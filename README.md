# xxw

<a href="https://github.com/996icu/996.ICU/blob/master/LICENSE">
    <img alt="License-Anti" src="https://img.shields.io/badge/License-Anti 996-blue.svg">
</a>
<a href="https://996.icu/#/zh_CN">
    <img alt="Link-996" src="https://img.shields.io/badge/Link-996.icu-red.svg">
</a>

| 名称 | 说明 |
|  ----  | ----  |
| 旧网址 | https://oss.sonatype.org |
| 新网址 | https://s01.oss.sonatype.org |
| 发布说明 | https://central.sonatype.org/publish/publish-guide/#deployment |
| 注册 | https://issues.sonatype.org/secure/Signup!default.jspa |
| 登录 | https://issues.sonatype.org/login.jsp |
| 示例 | https://issues.sonatype.org/browse/OSSRH-75894 |
| gpg4win | https://www.gpg4win.org/thanks-for-download.html |
| 手动上传公钥（需要验证邮件） | https://keys.openpgp.org/upload/ |
| 生成密钥  | gpg --gen-key |
| 查看秘钥 | gpg --list-key |
| 查看私钥 | gpg --list-secret-keys |
| 提取公钥 | gpg -a --export newkey > new-public-key.asc |
| 提取私钥 | gpg -a --export-secret-keys newkey > new-private-key.asc |
| 导入公钥或私钥  | gpg --import newkey |
| 单文件签名 | gpg --armor --detach-sign |
| 快照仓库 | https://s01.oss.sonatype.org/content/repositories/snapshots |
| 生产仓库 | https://s01.oss.sonatype.org/content/repositories/releases |

```
<repositories>
    <repository>
        <id>s01-releases</id>
        <url>https://s01.oss.sonatype.org/content/repositories/releases/</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
    </repository>
    <repository>
        <id>s01-snapshots</id>
        <url>https://s01.oss.sonatype.org/content/repositories/snapshots/</url>
        <releases>
            <enabled>false</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>
```
