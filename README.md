# practice
 practice 



## git 的使用
### 1. git add 之后想要把文件退出
    1. 针对单个文件 git restore --staged <file>
    2. 针对所有文件 git restore --staged .
### 2. commit之后怎么退回

    1. 如果是退回到add .之后, 使用 git reset --soft HEAD^ ,如果出现more？ 使用 git reset --soft HEAD^^  
            或者 git reset --soft cimmitId
    2. 如果直接退回add之前， 使用 git reset --hard HEAD^; 这个不要使用，因为回退之后，你新写的代码都没了 
### 3. commit回退到上一版本，又想继续使用新的版本
    1.先使用 git reflog 查看提交记录
    2. git reset --soft (hard) cimmitId 回到具体的提交记录
### 4. 工作流怎么合并
    拉下来的是master，先切换master，然后
    1. 如果是退回到add .之后, 使用 git reset --soft HEAD^ ,如果出现more？ 使用 git reset --soft HEAD^^
    2. 如果直接退回add之前， 使用 git reset --hard HEAD^; 这个不要使用，因为回退之后，你新写的代码都没了 



