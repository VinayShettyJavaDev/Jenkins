pipeline {
    agent any
    
    stages {
        stage('Input') {
            steps {
                script {
                    def firstNumber = input(
                        message: 'Enter the first number:',
                        parameters: [string(name: 'FIRST_NUMBER', defaultValue: '')]
                    )
                    def secondNumber = input(
                        message: 'Enter the second number:',
                        parameters: [string(name: 'SECOND_NUMBER', defaultValue: '')]
                    )
                    def operation = input(
                        message: 'Enter the operation (addition/subtraction/multiplication/division):',
                        parameters: [choice(name: 'OPERATION', choices: ['addition', 'subtraction', 'multiplication', 'division'])]
                    )

                    echo "Performing ${operation} on ${firstNumber} and ${secondNumber}"
                    
                    def result = 0
                    
                    switch (operation) {
                        case 'addition':
                            result = firstNumber.toInteger() + secondNumber.toInteger()
                            break
                        case 'subtraction':
                            result = firstNumber.toInteger() - secondNumber.toInteger()
                            break
                        case 'multiplication':
                            result = firstNumber.toInteger() * secondNumber.toInteger()
                            break
                        case 'division':
                            if (secondNumber.toInteger() == 0) {
                                error("Error: Division by zero")
                            }
                            result = firstNumber.toInteger() / secondNumber.toInteger()
                            break
                        default:
                            error("Error: Invalid operation")
                    }
                    
                    echo "Result: ${result}"
                }
            }
        }
    }
}

