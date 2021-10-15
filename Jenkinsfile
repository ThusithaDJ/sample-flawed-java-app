def regions = [:]
def environment = 'staging'
def map = ['staging':'us', 'prod':'eu']
def foo = ["1", "2", "3"]

def parallelStagesFromMap = foo.collectEntries {
    println('ParallelStage' + params.deploy_env)
    ["Build ${it}" : generateStage(it)]
}

def generateStage(bar) {
    return {
        stage("Build ${bar}") {
        println('generateStage' + params.deploy_env)
            echo "Building for ${bar}"
        }
        stage("test ${bar}") {
            echo "Building for ${bar}"
        }
    }
}

node {
    stage('config') {
        println('config '+params.deploy_env)
    }
    parallel parallelStagesFromMap

    generateStage("skipped") // no invocation, stage is skipped

    generateStage("nonparallel").call()
}