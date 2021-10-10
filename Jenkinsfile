pipeline {
  agent any
  stages {
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
                            properties([
                                parameters([
                                    choice(
                                        choices: ['ONE', 'TWO'],
                                        name: 'PARAMETER_01'
                                    ),
                                    booleanParam(
                                        defaultValue: true,
                                        description: '',
                                        name: 'BOOLEAN'
                                    ),
                                    text(
                                        defaultValue: '''
                                        this is a multi-line
                                        string parameter example
                                        ''',
                                         name: 'MULTI-LINE-STRING'
                                    ),
                                    string(
                                        defaultValue: 'scriptcrunch',
                                        name: 'STRING-PARAMETER',
                                        trim: true
                                    )
                                ])
                            ])
                            sh 'echo us destroy'
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
