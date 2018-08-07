#!groovy
node ('worker_node1') {
    stage('Source') { // Get code
        // get code from our Git repository
	checkout scm
    }
    stage('Compile') { // Compile and do unit testing
        // get Gradle HOME value
        def gradleHome = tool 'gradle4'
        // run Gradle to execute compile and unit testing
        sh "'${gradleHome}/bin/gradle' clean compileJava test"
    }
}
