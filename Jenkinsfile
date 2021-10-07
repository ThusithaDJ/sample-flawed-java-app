pipeline {
  agent any
  stages {
    stage('Init') {
      steps {
          sh 'gradle clean build'
      }
    }
    stage('Region') {
        parallel {
            stage('US') {
                parallel {
                    stage('Validate') {
                        parallel {
                            stages {
                                stage('Staging validate') {
                                    steps {
                                        sh 'gradle clean build'
                                    }
                                }
                                stage('Playground validate') {
                                    steps {
                                        sh 'gradle clean build'
                                    }
                                }
                            }
                        }
                    }
                }
            }
            stage('EU') {
                steps {
                    sh 'gradle clean build'
                }
            }
        }
    }
    stage('Deploy') {
        steps {
            sh 'gradle clean build'
        }
    }
  }
}
