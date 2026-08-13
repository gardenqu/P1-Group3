pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out deployment branch...'
                checkout scm
            }
        }

        stage('Test Jenkins') {
            steps {
                echo 'Jenkins is working!'
                sh 'whoami'
                sh 'docker --version'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker compose build'
            }
        }
    }
}
