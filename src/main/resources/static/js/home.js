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
    output.textContent = getCommandOutput(cmd);

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
}

function getCommandOutput(cmd) 
{
    switch (cmd.trim()) 
    {
        case "help": 
            return `
HELP SYSTEM OF JAVID SADIGLI'S WEBSITE

    help            Displays a list of commands that can be runned 
    clear           Clears the console
    cv              Downloads CV of Javid Sadigli
    about           Gives a description about Javid Sadigli
    contact         Shows contact information to reach Javid Sadigli
            `;

        case "about": 
            return `
About Javid Sadigli: 

        Experienced Software Engineer with a strong focus on Java and Spring Boot for building scalable, high-performance backend 
        systems. Skilled in designing and implementing RESTful APIs, developing microservices architectures, and working with both 
        SQL and NoSQL databases. Proficient in leveraging modern tools and platforms including Kubernetes for container 
        orchestration, and Prometheus with Grafana for monitoring and observability. Committed to writing clean, maintainable code 
        and collaborating effectively with cross-functional teams to deliver reliable and impactful software solutions.
            `;

        case "contact": 
            return `
Contact information of Javid Sadigli: 

    Email: j.sadigli@ufaz.az
    Phone number: +994 50 790 55 30
    LinkedIn: https://www.linkedin.com/in/cavidsad%C4%B1ql%C4%B1/
            `;

        default:
            return `Command not found: ${cmd}`;
    }
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
