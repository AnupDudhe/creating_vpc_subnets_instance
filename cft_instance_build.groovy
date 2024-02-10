pipeline {
    agent {
        label 'simple1'
    }
    stages {
        stage('Repository-pull') {
            steps {
                sh '''
                echo "your CFT is pulled from github repository"
                sudo git pull 'https://github.com/AnupDudhe/creating_vpc_subnets_instance.git'
                '''

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