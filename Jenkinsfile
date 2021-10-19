def regions = [:]
def environment = 'staging'
def foo = ["1", "2", "3"]
def targetEnv = 'staging'
def map = [ 'staging':['us', 'eu'],
            'dev':['us', 'eu']]

@NonCPS
def handleParams() {
    foo = params.deploy_env
    println('HandlePrams')
}

def parallelStagesFromMap = foo.collectEntries {
    println('ParallelStage' + params.deploy_env)
    ["Build ${it}" : generateStage(params.deploy_env,it)]
}

def generateStage(env, bar) {
    return {
        stage("Build ${bar}") {
            echo "Building ${env} for ${bar}"
        }
        stage("test ${bar}") {
            echo "Building for ${bar}"
        }
        stage('deploy') {
//             input (message: "Do you want to continue?")
        }
    }
}

def doDynamicParallelSteps(foos) {
    println('doDynamicParallelSteps')
    println(foos)
    def vals = foos.get('dev')
  tests = [:]
  for (f in vals) {
    def val = f
    tests["${f}"] = {
      node {
        stage("${val}") {
          echo '${f}'
        }
        stage("Build ${f}") {
            echo "Building ${f} for ${f}"
        }
        stage("test ${f}") {
            echo "Building for ${f}"
        }
      }
    }
  }
  parallel tests
}

node {
            def fool = ['stage':["1", "2"],
                        'dev':["1", "2", "3"]]
    try {
        stage('config') {
            println('config '+params.deploy_env)
            handleParams()
            targetEnv = params.deploy_env
            foo = params.deploy_env
        }
        //     parallel parallelStagesFromMap

        //     generateStage("skipped") // no invocation, stage is skipped
//         println(params.deploy_env)
//
//         stage ('Infrastructure') {
//             generateStage("sdfghdfg", "nonparallel").call()
//         }
//         stage ('Infrastructure') {
// //             generateStage("sdfghdfg", "nonparallel").call()
//             doDynamicParallelSteps(fool)
//         }

    } catch(err) {
        println("ERR: ${err}")
        currentBuild.result = 'FAILED'
    }
}