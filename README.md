# practice
 practice 



## git 的使用
### 1. git add 之后想要把文件退出
    1. 针对单个文件 git restore --staged <file>
    2. 针对所有文件 git restore --staged .
### 2. commit之后怎么退回
    1. 如果是退回到add .之后, 使用 git reset --soft HEAD^ ,如果出现more？ 使用 git reset --soft HEAD^^
    2. 如果直接退回add之前， 使用 git reset --hard HEAD^; 这个不要使用，因为回退之后，你新写的代码都没了 