pipelineJob("test") {
	description()
	keepDependencies(false)
	definition {
		cps {
 		  script """
def genText(lines){
    (1..lines).each{
       println "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur interdum fringilla interdum"
    }
    return true
}
parallel firstBranch: {
    genText(40000)
}

//, secondBranch: {
//    genText(20000)
}"""		}
	}
	disabled(false)
}
