pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from the repository
                checkout scm
            }
        }
        stage('Build') {
            steps {
                // Build the application
                sh './gradlew assemble'
            }
        }
        stage('Test') {
            steps {
                // Run tests
                sh './gradlew test'
            }
        }
    }

    post {
        always {
            // Archive test results and logs
            junit '**/build/test-results/**/*.xml'
            archiveArtifacts artifacts: '**/build/libs/*.jar', allowEmptyArchive: true
        }
        failure {
            // Notify on failure
            echo 'Build or tests failed!'
        }
    }
}
