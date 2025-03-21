let display = document.getElementById("display");
let errorMessage = document.getElementById("error-message");

let currentInput = "";
let previousInput = "";
let operator = "";

const MAX_DISPLAY_LENGTH = 15;

function appendNumber(number) {
    if ((previousInput.length + currentInput.length + operator.length) >= MAX_DISPLAY_LENGTH) {
        errorMessage.textContent = "Erro: Excesso de tamanho";
        return;
    }

    if (currentInput === "" && number === 0 && previousInput === "") return;

    if (previousInput !== "" && currentInput === "" && operator !== "") {
        currentInput = String(number);
        updateDisplay();
        return;
    }

    currentInput += String(number);
    updateDisplay();
}

function appendOperator(op) {
    if (currentInput === "" && previousInput === "") {
        currentInput = "0"; 
    }

    if (currentInput !== "") {
        previousInput = currentInput;
        currentInput = "";
        operator = op;
        updateDisplay();
    }
}

function clearDisplay() {
    currentInput = "";
    previousInput = "";
    operator = "";
    display.value = "";
    errorMessage.textContent = "";
}

function calculate() {
    if (currentInput === "" || previousInput === "") return;

    let result;
    try {
        result = eval(previousInput + operator + currentInput);
    } catch (error) {
        result = "Erro";
    }

    if (String(result).length > MAX_DISPLAY_LENGTH) {
        result = result.toExponential(3);
    }

    if (String(result).length > MAX_DISPLAY_LENGTH) {
        errorMessage.textContent = "Erro: Excesso de tamanho";
    } else {
        errorMessage.textContent = "";
    }

    display.value = previousInput + " " + operator + " " + currentInput + " = " + result;
    currentInput = result;
    previousInput = "";
    operator = "";
}

function updateDisplay() {
    if ((previousInput.length + currentInput.length + operator.length) > MAX_DISPLAY_LENGTH) {
        errorMessage.textContent = "Erro: Excesso de tamanho";
    } else {
        errorMessage.textContent = "";
    }

    display.value = previousInput + " " + operator + " " + currentInput;
}

function deleteCharacter() {
    if (currentInput.length > 0) {
        currentInput = currentInput.slice(0, -1);
    } else if (operator.length > 0) {
        operator = "";
    } else if (previousInput.length > 0) {
        previousInput = previousInput.slice(0, -1);
    }
    
    updateDisplay();
}
