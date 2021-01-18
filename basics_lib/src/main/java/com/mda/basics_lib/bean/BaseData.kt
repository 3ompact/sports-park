package com.mda.basics_lib.bean


/**
 *
 * git branch -f master HEAD~3
 *
 * git checkout HEAD~4
 *
 * git reset HEAD~1 对远程分支无效
 *
 * git revert HEAD  之后再推送远程仓库就可以实现与别人分享
 *
 * 吧其他地方的提交追加到HEAD上
 * git cherry-pick c2 c4
 *
 * git rebase -i HEAD
 *
 * git checkout -b foo o/master
 *
 * 设置远程分支  把foo分支关联o/master分支
 * git branch -u o/master foo
 *
 * git push origin foo ^ :master
 *
 * gti fetch origin foo
 *
 *
 * git fetch origin foo~1:bar
 *
 *
 * git fetch origin :bar
 * git pull origin master:foo
 */
data class BaseData(var id: String)
