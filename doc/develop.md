# 开发规范手册

### 1. 创建开发分支
基于当前master分支创建开发分支,开发分支名称`<昵称>/<版本>`,例如我的昵称是xjcooo, 分支功能号是v1,此时分支名称为`xjcooo/v1`,该命名规则可以用来区分开发者以及相同开发者委会多个功能点使用
创建分支命令:
```bash
// 创建分支
git branch xjcooo/v1
// 切换到分支
git checkout xjcooo/v1
```
or
```bash
// 新建分支xjcooo/v1,并直接切换至该分支
git checkout -b xjcooo/v1
```
### 2. 分支操作命令
#### 2.1 查看分支
```bash
// 查看本地所有分支
$ git branch
  master
* xjcooo/v1
// 查看所有分支包括远程分支(remotes前缀的为远程分支)
$ git branch -a
* master
  xjcooo/v1
  remotes/origin/HEAD -> origin/master
  remotes/origin/master
```
#### 2.2 提交分支到远端
```bash
$ git push --set-upstream origin xjcooo/v1
$ git branch -a
  master
* xjcooo/v1
  remotes/origin/HEAD -> origin/master
  remotes/origin/master
  remotes/origin/xjcooo/v1

```
#### 2.3 删除本地分支
```bash
// 该操作不能在当前xjcooo/v1分支上执行,需要切换到其他分支,建议切换到master分支上删除
$ git branch -d xjcooo/v1
$ git branch -a
* master
  remotes/origin/HEAD -> origin/master
  remotes/origin/master
  remotes/origin/xjcooo/v1
```
#### 2.4 删除远程分支
```bash
$ git push origin --delete xjcooo/v1
$ git branch -a
* master
  remotes/origin/HEAD -> origin/master
  remotes/origin/master
```
### 3. 分支提交代码
#### 3.1 查看当前分支代码修改状态
该命令可以查看当前新增的文件,修改的文件信息
```bash
$ git status
On branch master
Your branch is up-to-date with 'origin/master'.
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

        new file:   "\346\226\207\346\241\243\345\222\214sql/develop.md"

Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

        modified:   README.md
        modified:   "\346\226\207\346\241\243\345\222\214sql/develop.md"
```
#### 3.2 本地提交代码
```bash
// 将本地新增文件添加到提交列表
$ git add .
$ git status
On branch master
Your branch is up-to-date with 'origin/master'.
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

        modified:   README.md
        new file:   "\346\226\207\346\241\243\345\222\214sql/develop.md"
        
$ git commit -m "新增开发规范说明"
[master 422f357] 新增开发规范说明
 2 files changed, 95 insertions(+)
 create mode 100644 "\346\226\207\346\241\243\345\222\214sql/develop.md"

```
or
````bash
$ git commit -a -m "开发规范说明2"
[master 9fce0ce] 开发规范说明2
 1 file changed, 10 insertions(+), 1 deletion(-)

$ git status
On branch master
Your branch is ahead of 'origin/master' by 2 commits.
  (use "git push" to publish your local commits)
nothing to commit, working tree clean

````
#### 3.3 提交代码到远程仓库
```bash
$ git push
$ git status
On branch master
Your branch is up-to-date with 'origin/master'.
nothing to commit, working tree clean
```
### 4. 分支合并
该操作首先要确保分支代码已经`commit`,再切换到`master`分支,然后将本地`master`分支代码与远端代码同步,后面就可以发起合并操作了
合并完成并解决代码冲突后,就可以提交`master`分支代码,并`push`到远端.
具体操作如下:
```bash
// 查看分支代码提交状态
wangchh@user-PC MINGW64 /f/project/gitRep/hbec-fof-portal (xjcooo/v1)
$ git status
On branch xjcooo/v1
nothing to commit, working tree clean
// 切换到master分支
wangchh@user-PC MINGW64 /f/project/gitRep/hbec-fof-portal (xjcooo/v1)
$ git checkout master
Switched to branch 'master'
Your branch is up-to-date with 'origin/master'.
// 与远端代码同步
wangchh@user-PC MINGW64 /f/project/gitRep/hbec-fof-portal (master)
$ git pull
Already up-to-date.
// 合并分支
wangchh@user-PC MINGW64 /f/project/gitRep/hbec-fof-portal (master)
$ git merge xjcooo/v1
Updating d76434f..482d339
Fast-forward
 "\346\226\207\346\241\243\345\222\214sql/develop.md" | 8 +++++++-
 1 file changed, 7 insertions(+), 1 deletion(-)
// 查看master分支状态
wangchh@user-PC MINGW64 /f/project/gitRep/hbec-fof-portal (master)
$ git status
On branch master
Your branch is ahead of 'origin/master' by 2 commits.
  (use "git push" to publish your local commits)
nothing to commit, working tree clean
// 推送master分支到远端
wangchh@user-PC MINGW64 /f/project/gitRep/hbec-fof-portal (master)
$ git push
Counting objects: 8, done.
Delta compression using up to 4 threads.
Compressing objects: 100% (8/8), done.
Writing objects: 100% (8/8), 965 bytes | 0 bytes/s, done.
Total 8 (delta 6), reused 0 (delta 0)
To http://10.0.30.24/hbec-fof/hbec-fof-portal.git
   d76434f..482d339  master -> master

wangchh@user-PC MINGW64 /f/project/gitRep/hbec-fof-portal (master)
$ git status
On branch master
Your branch is up-to-date with 'origin/master'.
nothing to commit, working tree clean
```
