pipeline {
    agent any

    stages {

        stage('Test Jenkins') {
            steps {
                echo 'Jenkins is working!'
                sh 'whoami'
                sh 'docker --version'
                sh 'docker compose version'
            }
        }

        stage('Unit Tests') {
            steps {
                sh '''
                    cd manager_app
                    mvn test
                '''
            }
        }

        stage('Build Docker Images') {
            steps {
                sh 'docker compose build'
            }
        }

        stage('Start Application') {
            steps {
                sh 'docker compose up -d'
            }
        }

    }
}
