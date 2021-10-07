pipeline {
  agent any
  stages {
    stage('Init') {
      steps {
          sh 'gradle clean build'
      }
    }
    stage('Infrastructure') {
        parallel {
            stage('US Staging') {
                stages {
                    stage('Validate') {
                        steps {
                            sh 'gradle clean build'
                        }
                    }
                    stage('Plan') {
                        steps {
                            sh 'gradle clean build'
                        }
                    }
                    stage('Deploy') {
                        steps {
                            sh 'gradle clean build'
                        }
                    }
                }
            }
            stage('US Playground') {
                stages {
                    stage('Validate') {
                        steps {
                            sh 'gradle clean build'
                        }
                    }
                    stage('Plan') {
                        steps {
                            sh 'gradle clean build'
                        }
                    }
                    stage('Deploy') {
                        steps {
                            sh 'gradle clean build'
                        }
                    }
                }
            }
        }
    }
  }
}
