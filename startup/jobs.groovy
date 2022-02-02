pipelineJob("test-orig") {
  description()
  keepDependencies(false)
  definition {
    cps {
       script '''
def genText(lines){
    (1..lines).each{
       println "${it}: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur interdum fringilla interdum"
    }
    return true
}
parallel firstBranch: {
    genText(20000)
}, secondBranch: {
    genText(20000)
}
      '''
    }
  }
  disabled(false)
}

pipelineJob("test-with-10ms-sleep") {
  description()
  keepDependencies(false)
  definition {
    cps {
       script '''
import com.cloudbees.groovy.cps.NonCPS

@NonCPS
def genText(lines){
    def range=(1..lines)
    range.each{
       println "${it}: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur interdum fringilla interdum"
    }
    range.sleep(10)
    return true
}
genText(20000)
       '''
     }
  }
  disabled(false)
}

pipelineJob("test-using-external-process") {
  description()
  keepDependencies(false)
  definition {
    cps {
       script '''
def genText(lines){
    sh """#!/bin/bash

for i in \\$(seq ${lines}); do
    echo "\\${i}/${lines}: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur interdum fringilla interdum"
done
    """
    return true
}
node(){
    parallel firstBranch: {
        genText(20000)
    }, secondBranch: {
        genText(20000)
    }
}
       '''
     }
  }
  disabled(false)
}

pipelineJob("test-using-external-process-with-rando") {
  description()
  keepDependencies(false)
  definition {
    cps {
       script '''
def genText(lines){
    sh """#!/bin/bash

for i in \\$(seq ${lines}); do
    echo "\\${i}/${lines}: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur interdum fringilla interdum"
    sleep 0.0\\$((RANDOM % 99 + 1))
done
    """
    return true
}
node(){
    parallel firstBranch: {
        genText(20000)
    }, secondBranch: {
        genText(20000)
    }
}
       '''
     }
  }
  disabled(false)
}
