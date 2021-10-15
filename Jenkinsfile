def regions = [:]
def environment = 'staging'
def map = ['staging':'us', 'prod':'eu']
def foo = ["1", "2", "3"]

def parallelStagesFromMap = foo.collectEntries {
    ["Build ${it}" : generateStage(it)]
}

def generateStage(bar) {
    return {
        stage("Build ${bar}") {
            echo "Building for ${bar}"
        }
        stage("test ${bar}") {
            echo "Building for ${bar}"
        }
    }
}

node {
    parallel parallelStagesFromMap

    generateStage("skipped") // no invocation, stage is skipped

    generateStage("nonparallel").call()
}