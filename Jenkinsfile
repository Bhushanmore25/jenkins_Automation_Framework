pipeline {
    agent any

    environment {
        JMETER_HOME = "D:\\Capgemini\\ApacheJemter\\apache-jmeter-5.6.3"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git 'https://github.com/Bhushanmore25/jenkins_Automation_Framework.git'
            }
        }

        stage('Verify Environment') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Verify Selenium Server (localhost)') {
            steps {
                echo 'Checking Selenium Grid on localhost...'
                bat 'powershell -Command "(Invoke-WebRequest http://localhost:4444/status).StatusCode"'
            }
        }

        stage('Clean & Test') {
            steps {
                bat 'mvn clean test'
            }
        }

        stage('Run JMeter Test') {
            steps {
                bat '%JMETER_HOME%\\bin\\jmeter.bat -n -t jmeter/testplans/get_test.jmx -l jmeter/results/result.jtl'
            }
        }

        stage('Generate Allure Report') {
            steps {
                bat 'allure generate allure-results --clean -o allure-report'
            }
        }

        stage('Publish Allure Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
            }
        }
    }

    post {
        success {
            echo 'Build SUCCESS 🚀'
        }
        failure {
            echo 'Build FAILED ❌'
        }
    }
}