pipeline {
    agent {
        label 'simple1'
    }
    stages {
        stage('Repository-pull') {
            steps {
                  checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: '32e52d68-71e5-4d1b-bef5-053eec1f5c3b', url: 'https://github.com/AnupDudhe/creating_vpc_subnets_instance.git']])

            }
        }
        stage('AWS Configuration') {
            steps {
                sh '''
                sudo apt install awscli -y
                '''
            }
        }
        stage('Triggering_the_cft') {
            steps {
                sh '''
                sudo aws cloudformation create-stack \
                 --stack-name my-stack \
                 --template-body file:///home/ubuntu/workspace/anup/newvpcTemp.yml --region us-east-1
                '''
            }
        }
    }
}


