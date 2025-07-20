const terminalPromptText = "javidsadigli:~$ ";

const terminal = document.getElementById("terminal");
let currentInput = null;

function addPrompt() {
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

  inputArea.addEventListener("keydown", function (e) {
    if (e.key === "Enter") {
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

function runCommand(cmd) {
  const output = document.createElement("div");
  output.textContent = getCommandOutput(cmd);
  if (cmd.trim() === "clear") {
    terminal.innerHTML = "";
  } else {
    terminal.appendChild(output);
  }
  addPrompt();
}

function getCommandOutput(cmd) {
  switch (cmd.trim()) {
    default:
      return `Command not found: ${cmd}`;
  }
}

// Focus input on any click
document.addEventListener("click", () => {
  if (currentInput) currentInput.focus();
});

addPrompt();
