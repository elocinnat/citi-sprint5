def projectName = 'sprint5webapp'
def version = "0.0.${currentBuild.number}"
def dockerAngularImageTag = "angular-app:${version}"
def dockerSpringBootImageTag = "spring-boot:${version}"

pipeline {
    agent any

    stages {

        stage('Build docker images') {
            steps {
                sh "docker build -f Dockerfile-angular -t ${dockerAngularImageTag} ."
                sh "docker build -f Dockerfile-spring-boot -t ${dockerSpringBootImageTag} ."
            }
        }

        stage('Deploy images To OpenShift') {
            steps {
                sh "oc login https://localhost:8443 --username admin --password admin --insecure-skip-tls-verify=true"
                sh "oc new-project ${projectName} || oc project ${projectName}"
                sh "oc delete all --selector app=${projectName} || echo 'Unable to delete all previous OpenShift resources'"
                sh "oc new-app ${dockerAngularImageTag} -l version=${version}"
                sh "oc new-app ${dockerSpringBootImageTag} -l version=${version}"
                sh "oc expose svc/angular-app --port=8080"
            }
        }
    }
}