# 安裝 Ubuntu Server 14.04 x64 
下載 [Ubuntu Server 14.04 x64](http://releases.ubuntu.com/14.04/ubuntu-14.04.4-server-amd64.iso) 


1.使用光碟開機後顯示如下圖，請選擇語言(English)

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-language.png)

2.選擇 Install Ubuntu Server

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-Install-Ubuntu-Server.png)

3.選擇 English

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-Select-language-for-Installation.png)

4.選擇所在地區，「台灣」

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-Select-Location.png)

5.選擇是否設定鍵盤選擇，「No」

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-Configure-the-keyboard.png)

6.選擇鍵盤使用 English(US)

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-layout-of-keyboard-varies-per-country.png)

7.選擇鍵盤 English 即可

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-select-keyboard-for-machine.png)

8.自動設定網路

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-loading-addtional-component.png)

9.設定使用者全名

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-setup-users-and-passwords.png)

10.設定帳號

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-setup-users-and-passwords-2.png)

11.設定密碼

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-setup-password.png)

12.再輸入一次密碼

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-re-enter-password.png)

13.選擇是否加密 home，選擇「No」

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-Encrypt-Home-Directory.png)

14.設定時區

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-time-zone.png)

15.設定 Partition，選擇 Gided -use entire disk and set up LVM

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-partition-disk.png)

16.選擇硬碟

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-Partition-LVM.png)

17.是否開始執行，「Yes」

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-configure-LVM.png)

18.設定大小，預設即可

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-lvm-partition-2.png)

19.開始安裝，選擇「Yes」

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-end-of-partition-LVM.png)

20.執行中

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-installing-system-.png)

21.設定 http proxy, 直接選擇「Contiune」

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-set-proxy.png)

22.是否自動更新，no automatic updates

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-configuring-taskel.png)

23.基本安裝都是預設設定，只有下圖紅框區域需要注意，需要安裝 Tomcat Java server

![](http://i.imgur.com/hiiKzYP.png)

24.Tomcat Java server 安裝中

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-install-software.png)

25.安裝 boot loader ， 直接選擇 「Yes」

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-install-boot-loader.png)

26.Finish畫面，如下圖所示，選擇 「Contiune」 即可完成設定

![](http://ubuntuserverguide.com/wp-content/uploads/2014/04/Ubuntu-Server-14.04-Finishing-Installation.png)

# 安裝 Jenkins
    wget -q -O - https://jenkins-ci.org/debian/jenkins-ci.org.key | sudo apt-key add -
    sudo sh -c 'echo deb http://pkg.jenkins-ci.org/debian-stable binary/ > /etc/apt/sources.list.d/jenkins.list'
    sudo apt-get update
    sudo apt-get install jenkins

1.修改 jenkins port 並把 HTTP_PORT 修改成 8888

    sudo vim /etc/default/jenkins

![](http://i.imgur.com/6Vv5EPV.png)


2.重新啟動 Jenkins

    sudo service jenkins restart

# 安裝 git

    sudo apt-get install git

# 安裝 vim 

    sudo apt-get install vim


# 設定 Tomcat 使用者

    sudo vim /etc/tomcat7/tomcat-users.xml

設定 CI 上傳 war 的使用者帳號與密碼，範例

- 帳號:slm2016 
- 密碼:teddysoft

![](http://i.imgur.com/DCdyJaY.png)

重新啟動 tomcat

    sudo service tomcat7 restart

# 安裝 mysql server

	sudo apt-get install mysql-server

1.輸入 root 密碼，密碼範例為 teddysoft

![](http://i.imgur.com/VdlZwmO.png)

2.再輸入一次密碼

![](http://i.imgur.com/C3NzjOK.png)

3.安裝完成

![](http://i.imgur.com/BGUOi8I.png)

# 安裝 JDK

	sudo add-apt-repository ppa:webupd8team/java
	sudo apt-get update
	sudo apt-get install oracle-java8-installer

# 設定 Tomcat 連結 JDK

	sudo vim /etc/default/tomcat7

修改 JAVA_HOME 
	
![](http://i.imgur.com/losH7ko.png)

# 安裝 Server 中文支援

	sudo apt-get install language-pack-zh-hant
    sudo dpkg-reconfigure locales
    sudo service tomcat7 restart

# 安裝 標楷體

安裝 [WinSCP](https://winscp.net/download/winscp577setup.exe)

開啟 WinSCP 如圖，填入伺服器位置與帳號密碼

![](http://i.imgur.com/yl3K0zN.png)

找自己的字形位置先按下

![](http://i.imgur.com/zcjKe42.png)

輸入位置

	C:\Windows\Fonts

![](http://i.imgur.com/rVlvooJ.png)

找到伺服器的字型位置

![](http://i.imgur.com/guszhtW.png)
![](http://i.imgur.com/vr6ejPm.png)

找到 kaiu.ttf Upload

![](http://i.imgur.com/vS9AbEh.png)

按下 OK

![](http://i.imgur.com/3r6Q1zQ.png)

把字型複製到 Server 的字型目錄

	cd
	mv kaiu.ttf /usr/share/fonts/truetype/

重新讀取字形

	sudo fc-cache -f -v

重新開啟 tomcat

	sudo service tomcat7 restart

# Mysql 設定

登入 mysql
	mysql -p -uroot

它會請你輸入密碼不過不會顯示，輸入完按下 enter，這邊密碼為 teddysoft

建立 DB

	CREATE DATABASE SLM2016;

建立使用者

	CREATE USER 'SLM2016'@'localhost' IDENTIFIED BY 'Teddysoft';

給予使用者 DB 權限

	GRANT ALL PRIVILEGES ON SLM2016.* TO 'SLM2016'@'localhost';

離開 mysql command

	Ctrl + c

下載 table sql

	wget

匯入 table 

	mysql -u SLM2016 -p SLM2016 < SLM2016.sql

#Jenkins基本系統設定

1.點選左方目錄的「管理Jenkins」，進入後點選「管理外掛程式」，如下圖所示:

![](http://i.imgur.com/IVWQXpH.jpg)

2.點選上方「可用的」後，在右上角過濾條件輸入gitHub plugin、	
Deploy to Websphere container Plugin,並將此檔案打勾，如下圖所示:

![](http://i.imgur.com/uw2kwCA.jpg)
![](http://i.imgur.com/VFjFpQE.jpg)

3.勾選完畢後按下「直接安裝」，就會開始執行安裝程序了，如下圖所示:

![](http://i.imgur.com/s7XX9Mk.jpg)

4.安裝完畢後，點選「當安裝完成且沒有工作正在執行時，重啟Jenkins」的選項，之後回到首頁將會看到正在重啟頁面，如下圖所示:

![](http://i.imgur.com/to5TBJU.jpg)

5.我們可以回到「管理Jenkins」->「管理外掛程式」->「已安裝」->「過濾條件搜尋gitHub plugin」，即可看到已經安裝成功，如下圖所是:

![](http://i.imgur.com/4NSmVIy.jpg)

6.進入「管理Jenkins」->「設定系統」，看到「JDK」地方，點選新增JDK，填入JDK名稱(自定義)並勾選自動安裝以及我同意授權內容的選項，版本不需改變(8u92版)，如下圖所示:

![](http://i.imgur.com/33KCub3.jpg)

7.看到「Ant」地方，填入Ant名稱(自定義)，勾選自動安裝選項，版本不需改變(1.9.7)，如下圖所示:

![](http://i.imgur.com/KvP9jwO.jpg)

#開新專案&專案設定

1.安裝完畢Jenkins後，開啟畫面並點選「建立新工作」，如下圖所示:

![](http://i.imgur.com/BqMytva.jpg)

2.點選後，進入後畫面如下圖所示:

![](http://i.imgur.com/qBLGlsf.jpg)

3.請輸入「專案名稱」以及勾選「建置Free-Style軟體專案」，
如下圖紅框內容所示:

![](http://i.imgur.com/hL8MO7a.jpg)

4.確認輸入後，會進入設定組態的畫面，如下圖所示:

![](http://i.imgur.com/pLtuid3.jpg)

5.在「GitHub project」選項中打勾，並在「Project url」中填入git的位址，如下圖所示:

![](http://i.imgur.com/m1s4XVw.jpg)

6.在「原始碼管理」的地方，點選「Git」,並在「Repositories」底下的「Repository URL」填入git的位址，「Credentials」則維持不變，在「Branches to build」底下的「Branch Specifier」也維持不變，如下圖所示:

![](http://i.imgur.com/pPz24Xi.jpg)

7.在「建置觸發程序」選項中，將「Build when a change is pushed to GitHub」以及「輪詢SCM」兩個選項打勾，並設定輪詢的時間，以下圖為例子輪詢設定為「H/30 * * * *」，即每30分鐘進行輪詢一次，而H此符號可以將系統排程平均分擔一點，不會讓排程全部擠到同一個時間點上，詳細的設定可以開啟右方的藍色問號圖式加以觀看:

![](http://i.imgur.com/bH7GUVh.jpg)

8.在「建置」這部分，點選下方的「新增建置步驟」，如下圖:

![](http://i.imgur.com/QcZjAyU.jpg)

9.點選「叫用Ant」後，並點選右方「進階」按鈕，會出現以下畫面如圖所示:

![](http://i.imgur.com/J7MU2wp.jpg)

10.在「Ant版本」中選擇你之前所安裝的版本，以本例所示選擇1.9.7版，在「Target」中填入build-war JUnit，在「建置檔」中填入SLM2016/build.xml來完成設定，如下圖所示:

![](http://i.imgur.com/8kCxHkE.jpg)

11.在「建置後動作」中點選「發布JUnit測試結果報告」，在「測試報告XML」中填入 SLM2016/build/report/*.xml ，下面會顯示紅色表示正常，如下圖所示:

![](http://i.imgur.com/nerWtpn.jpg)

12.在「建置後動作」中點選「新增建置後動作」，選擇「Deploy war/ear to a container」，選擇後如下圖所示:

![](http://i.imgur.com/Qtx2p2u.jpg)

13.將「Containers」的「Add Container」點選展開，選擇Tomcat 7.X，如下圖所示:

![](http://i.imgur.com/diMJLlI.jpg)

14.在「WAR/EAR files」欄位中輸入War檔位置，以本例輸入SLM2016/build/war/SLM2016.war，在「Tomcat 7.X」底下欄位「Manager user name」中輸入 slm2016 、「Manager password」中填入 teddysoft 、「Tomcat URL」中輸入http://45.32.62.194:8080，即可完成設定，如下圖所示:

![](http://i.imgur.com/Blu73W3.jpg)

15.都輸入完成後即可按下下方的「儲存」，即完成專案的設定

![](http://i.imgur.com/ZAv0lX9.png)

#執行專案

1.進入專案後(SLM2016)，點選左方的馬上建置，如下圖所示:

![](http://i.imgur.com/mAX1M4G.jpg)

2.第一次執行會安裝JDK以及Ant所以會跑較長時間，如下圖所示:

![](http://i.imgur.com/jm8z1Rc.jpg)

3.執行成功即顯示藍燈，灰燈顯示是使用者停止，黃燈顯示是UnitTest失敗，紅燈則是代表執行失敗，如下圖所示:

![](http://i.imgur.com/eXSi4bt.jpg)

