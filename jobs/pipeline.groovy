pipelineJob('app') {
  displayName('app')
  logRotator {
    numToKeep(5)
  }
    parameters{
      gitParameter{
        name('GIT_BRANCH')
        defaultValue('main')
        description('Branch or tag to use for jobs')
        type('PT_BRANCH_TAG')
        branch('')
        branchFilter('origin/(.)')
        tagFilter('')
        sortMode('DESCENDING_SMART')
        selectedValue('NONE')
        useRepository('')
        quickFilterEnabled(true)
      }
      booleanParam('FAIL_ON_XRAY', true, 'Fail the build if xray scan returns violations')
    }
  definition {
    cpsScm {
      scm {
        git {
          remote {
            github('fedorchukivan/devops-app')
          }
          branches('${GIT_BRANCH}')
        }
      }
      scriptPath('Jenkinsfile')
      lightweight(false)
    }
  }
}