pipeline {
    agent any

    stages {

        stage('Test Jenkins') {
            steps {
                echo 'Jenkins is working!'
                sh 'whoami'
                sh 'docker --version'
            }
        }
    }
}