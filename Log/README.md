# 在线日志分析
## 日志分析的常用命令
### cat
cat 用户显示文件内容，无法交互。

```shell
// 显示文件所有内容
cat access.log
```

```shell
// 显示文件所有内容，带行号
cat -n access.log
```
### more
more 比 cat 多了翻页功能

```shell
// 显示文件内容
more access.log

下一行：Enter
下一屏：F
上已屏：B
```

### less
比more多了查询功能，查询结果高亮

```shell
less access.log

按/键，输入字符串，按Enter开始搜索
```

### tail
显示文件尾巴
```shell
// 显示默认尾几行
tail access.log

// 显示尾两行
tail -n2 access.log

// 不断输出
tail -f access.log

// 不断输出尾20行
tail -n20 f access.log
tail -20f access.log
```

### head
显示文件头
```shell
// 显示开头两行
head -n2 access.log
```

### sort
排序
```shell
// 根据每行行头单词进行字符排序
sort access.log

// 根据每行行头数字进行数字排序
sort -n access.log

// 逆序
sort -r access.log

// 根据第二列数字排序， -t ' ' 是以空格分隔，-k 2 是第二列，-n 是以数字排序。
sort -k 2 -t ' ' -n access.log
```

### wc
可以统计字符数、字数、行数等
```shell
// 统计行数
wc -l access.log

// 统计字节数
wc -c access.log

// 统计最长行的长度
wc -L access.log

// 统计文件字数
wc -w access.log
```

### uniq
查看重复出现的行（相邻两行重复出现），一般和sort命令一起使用
```shell
// 统计重复行出现（1次或多次都会被统计）的次数：-c 统计出现次数
sort access.log | uniq -c

// 统计只出现一次的行：-u
sort access.log | uniq -c -u

// 统计只出现2次或以上的行：-d
sort access.log | uniq -c -d
```

### grep
查找文件内容并打印出来
```shell
// 查找文件出现qq的地方
grep 'qq' access.log

// 查找文件出现qq的地方，并把行数打印出来
grep 'qq' -c access.log

// 正则，查找以G开头，T结尾的字符串
grep 'G.*T' access.log
```

### find
文件查找
```shell
// 在/home/my路径下查找文件名为access.log的文件，打印出该文件的全路径
find /home/my -name access.log

// 在/home/my路径下查找文件后缀为.log的文件，打印出该文件的全路径
find /home/my -name "*.log"

// 递归打印当前目前所有文件
find . -print
```

### expr
表达式求值
```shell
// 乘法，需要转义
expr 10 /* 3

// 除法
expr 10 % 3

// 加法
expr 10 + 3

// 字符出现的位置，结果为5
expr index "www.qq.com" qq

// 字符串的长度，结果为14
expr index "this is a test"
```

### tar
归档文件（压缩）
```shell
// 指定文件/目录名称进行打包（-f），生成新的包，源文件不会被删除（-c）
tar -cf aaa.tar detach tmp

// 列出包中文件的名称
tar -t aaa.tar

// 解压
tar -x aaa.tar
```

### curl
访问网站
```shell
// 发起网页请求
curl http://www.google.com

// 返回的数据待网站的header
curl -i http://www.google.com

// 只返回网页的header信息
curl -I http://www.google.com
```

### sed
修改文本
```shell
// 将行中的xx改成yahoo：s
sed 's/xxx/yahoo/' access.log

// 输出第二行，第六行之间的行：p
sed -n '2,6p' access.log

// 删除带qq字符的行：d
sed 'qq/d' access.log

// 输出每一行，带行号：=
sed '=' access.log

// 在行首插入文本myhead：i
sed -e 'i\myhead' access.log

// 在行尾插入文本myend：a
sed -e 'a\myend' access.log

// 对匹配的行进行替换，查找google匹配的行，用hell对匹配的行进行替换：c
sed -e '/google/c\hello' access.log

// 多个命令，打印1到5行，打印1-5行的行号
sed -n '1,5p;1,5=' access.log



```

