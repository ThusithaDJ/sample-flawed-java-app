pipeline {
  agent any
  stages {
    parameters{
        choice( name: 'deploy_env',
                choices: 'dev\nstage\nprod',
                description: 'Choose deployment location')
        booleanParam(
                        name: 'User Approval Needed?',
                        defaultValue: true,
                        description: 'User approval required stages')
        string(
                name: 'String param',
                defaultValue: 'Default',
                description: 'description')
    }
    stage('Init') {
      steps {
          sh 'echo init'
      }
    }
    stage('Infrastructure') {
        parallel {
            stage('US Staging') {
                stages {
                    stage('Validate') {
                        steps {
                            sh 'echo us staging validate'
                        }
                    }
                    stage('Plan') {
                        steps {
                            sh 'echo us plan'
                        }
                    }
                    stage('Deploy') {
                        steps {
                            sh "Deploying to ${params.deploy_env}"
                        }
                    }
                }
            }
            stage('US Playground') {
                stages {
                    stage('Validate') {
                        steps {
                            sh 'echo play validate'
                        }
                    }
                    stage('Plan') {
                        steps {
                            sh 'echo play plan'
                        }
                    }
                    stage('Deploy') {
                        steps {
                            sh 'echo play destroy'
                        }
                    }
                }
            }
        }
    }
  }
}

