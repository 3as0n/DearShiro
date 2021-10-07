# DearShiro

人类低质量代码，就当练个手。

目前已完成:
- Key的探测
- 可用Gadget的探测
- 利用Gadget执行命令


后续todo:
- 命令行工具化
- 图形化？
- 修复某些未知BUG


由于Shiro在反序列化的时候无法加载除Java本身自带的数组，所以可利用的Gadget有限。

目前可用Gadget:
- CommonsBeanUtilsNoCC
- CommonsCollectionsK1
- CommonsCollectionsK2
- JRMPClient（需要在VPS上首先开启一个JRMPServer）
