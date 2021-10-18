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

node {
    try {
        stage('config') {
            println('config '+params.deploy_env)
            handleParams()
            targetEnv = params.deploy_env
            foo = params.deploy_env
        }
        //     parallel parallelStagesFromMap

        //     generateStage("skipped") // no invocation, stage is skipped

        generateStage("nonparallel").call()
    } catch(err) {
        println("ERR: ${err}")
        currentBuild.result = 'FAILED'
    }
}