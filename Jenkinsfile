#!groovy
pipeline {
    agent { label 'worker_node1' }
    stages {
        stage('Source') { // Get code
            steps {
                // get code from our Git repository
                checkout scm
            }
        }
        stage('Compile') { // Compile and do unit testing
            tools {
                gradle 'gradle4'
            }
            steps {
                // run Gradle to execute compile and unit testing
                sh 'gradle clean compileJava test'
            }
        }
    }
}
