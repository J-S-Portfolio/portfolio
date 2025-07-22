const terminalPromptText = "javidsadigli:~$ ";

const terminal = document.getElementById("terminal");
let currentInput = null;

let commandHistory = [];
let currentCommandIndex = 0, lastCommandIndex = 0;

function addPrompt() 
{
    const line = document.createElement("div");
    line.className = "line";

    const prompt = document.createElement("span");
    prompt.className = "prompt";
    prompt.textContent = terminalPromptText;

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
            const command = inputArea.innerText.trim();
            line.innerHTML = `<span class="prompt">${terminalPromptText}${command}</span>`;

            currentCommandIndex = lastCommandIndex;

            if(commandHistory[lastCommandIndex - 1] != command)
            {
                commandHistory.push(command);
                currentCommandIndex = ++lastCommandIndex;
            }

            runCommand(command);
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
        .then(cmdOutput => {
            output.textContent = cmdOutput;

            if (cmd.trim() === "clear") 
            {
                terminal.innerHTML = "";
            } 
            else if(cmd.trim() === "cv")
            {
                output.textContent = "Downloading CV..."; 
                terminal.appendChild(output);
                downloadFile("/cv/javid_sadigli_cv.pdf", "JavidSadigli.pdf");
            }
            else 
            {
                terminal.appendChild(output);
            }

            addPrompt();
        });
}

async function getCommandOutput(cmd) 
{
    const response = await fetch(`/api/v1/console/command-output?command=${cmd.trim()}`)

    return response.text();
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
