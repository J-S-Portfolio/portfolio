const terminalPromptText = "javidsadigli:~$ ";

const terminal = document.getElementById("terminal");
let currentInput = null;

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
            runCommand(command);
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

// Focus input on any click
document.addEventListener("click", () => {
    if (currentInput) currentInput.focus();
});

addPrompt();
