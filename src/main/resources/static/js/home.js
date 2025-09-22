const terminalPromptText = "javidsadigli:~$ ";

const terminal = document.getElementById("terminal");
let currentInput = null;

let commandHistory = [];
let currentCommandIndex = 0, lastCommandIndex = 0;

let emailSendingMode = false, emailSendingModeWillBeDisabled = false; 
let emailPrompts = [
    "Enter your full name: ", 
    "Enter your email address: ", 
    "Enter your phone number: ",
    "Enter the subject: ", 
    "Enter your message: ",
    "Are you sure you want to send the email? (yes/no): "
];
let emailKeys = ["userFullName", "userEmail", "userPhone", "subject", "body"];
let emailData = [];

function addPrompt() 
{
    const line = document.createElement("div");
    line.className = "line";

    const promptTextContent = emailSendingMode ? emailPrompts[emailData.length] : terminalPromptText;
    const prompt = document.createElement("span");
    prompt.className = "prompt";
    prompt.textContent = promptTextContent;

    const inputWrapper = document.createElement("span");
    inputWrapper.className = "input-wrapper";

    const inputArea = document.createElement("span");
    inputArea.className = "input-area";
    inputArea.contentEditable = true;
    inputArea.spellcheck = false;

    inputArea.addEventListener("keydown", function (e) 
    {
        if (e.key === "Enter") 
        {
            e.preventDefault(); // Prevent newline insertion
            const input = inputArea.innerText.trim();
            line.innerHTML = `<span class="prompt">${promptTextContent}${input}</span>`;

            if(emailSendingMode)
            {
                if(emailData.length < emailPrompts.length - 1) 
                {
                    emailData.push(input);
                }
                else 
                {
                    emailSendingModeWillBeDisabled = true; 
                    if(input.toLowerCase() === "yes")
                    {
                        const requestBody = prepareEmailRequestBody(emailKeys, emailData);
                        sendEmail(requestBody)
                            .then(response => {
                                const output = document.createElement("div");
                                output.textContent = "Email has been sent successfully.";
                                terminal.appendChild(output);
                                emailData = [];
                                addPrompt();
                            }); 
                    }
                    else 
                    {
                        const output = document.createElement("div");
                        output.textContent = "Email sending cancelled.";
                        terminal.appendChild(output);
                        emailData = [];
                        addPrompt();
                        return;
                    }
                }
            }
            else 
            {
                currentCommandIndex = lastCommandIndex;

                if(commandHistory[lastCommandIndex - 1] != input)
                {
                    commandHistory.push(input);
                    currentCommandIndex = ++lastCommandIndex;
                }
            }

            if(!emailSendingMode)
            {
                runCommand(input);
            }
            else if(emailSendingModeWillBeDisabled)
            {
                emailSendingModeWillBeDisabled = false; 
                emailSendingMode = false; 
            }
            else 
            {
                addPrompt();
            }
        }

        if(e.key === "ArrowUp")
        {
            e.preventDefault();

            if(currentCommandIndex > 0)
            {
                currentCommandIndex--; 
                inputArea.innerText = commandHistory[currentCommandIndex];
                placeCaretAtEnd(inputArea);
            }
        }

        if(e.key === "ArrowDown")
        {
            e.preventDefault();

            if(currentCommandIndex < lastCommandIndex - 1)
            {
                currentCommandIndex++; 
                inputArea.innerText = commandHistory[currentCommandIndex];
                placeCaretAtEnd(inputArea);
            }
            else if(currentCommandIndex == lastCommandIndex - 1)
            {
                currentCommandIndex++; 
                inputArea.innerText = "";
                placeCaretAtEnd(inputArea);
            }
        }
    });

    inputWrapper.appendChild(inputArea);
    line.appendChild(prompt);
    line.appendChild(inputWrapper);
    terminal.appendChild(line);

    currentInput = inputArea;
    inputArea.focus();
    terminal.scrollTop = terminal.scrollHeight;
}

function runCommand(cmd)
{
    const output = document.createElement("div");

    if(cmd.trim() === '')
    {
        addPrompt();
        return; 
    }

    getCommandOutput(cmd)
        .then(cmdResponse => {
            
            if(cmdResponse.outputType === "COMMAND_NOT_FOUND")
            {
                output.textContent = cmdResponse.commandOutput.commandNotFoundMessage;
                terminal.appendChild(output); 
            }
            else if(cmdResponse.outputType === "TEXT_OUTPUT")
            {
                output.textContent = cmdResponse.commandOutput.text; 
                terminal.appendChild(output); 
            }
            else if(cmdResponse.outputType === "ACTION")
            {
                if(cmdResponse.commandOutput.actionType === "CLEAR_SCREEN")
                {
                    terminal.innerHTML = ""; 
                }
                else if(cmdResponse.commandOutput.actionType === "DOWNLOAD_FILE")
                {
                    output.textContent = "Downloading file..."; 
                    terminal.appendChild(output);
                    downloadFile(
                        cmdResponse.commandOutput.action.filePath,
                        cmdResponse.commandOutput.action.fileName
                    );
                }
                else if(cmdResponse.commandOutput.actionType === "SEND_EMAIL")
                {
                    emailSendingMode = true;
                }
            }

            addPrompt();
        });
}

async function getCommandOutput(cmd) 
{
    const response = await fetch(`/api/console/command-output?command=${cmd.trim()}`)
    return response.json();
}

async function sendEmail(body)
{
    const response = await fetch
    (
        '/api/mail/send-email', 
        {
            method: 'POST',
            headers: { 'Content-Type': 'application/json', "Accept": "*/*" },
            body: JSON.stringify(body)
        }
    );

    return response;
}

function prepareEmailRequestBody(keys, values)
{
    const body = {};
    for(let i = 0; i < keys.length; i++)
    {
        body[keys[i]] = values[i];
        console.log(body);
    }
    return body;
}

function downloadFile(url, filename) 
{
    const link = document.createElement("a");
    link.href = url;
    link.download = filename;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}

function placeCaretAtEnd(el) 
{
    const range = document.createRange();
    const sel = window.getSelection();
    range.selectNodeContents(el);
    range.collapse(false);
    sel.removeAllRanges();
    sel.addRange(range);
}

// Focus input on any click
document.addEventListener("click", () => {
    if (currentInput) currentInput.focus();
});

addPrompt();
