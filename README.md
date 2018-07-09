ICPlane

Version 1.0 June 28th 2018

Internet Controlled Plane

A collection of three software modules that allow the user to control a model airplane without traditional RxTx hardware.

* Use this software at your own risk, please read this document carefully before use *

by Andrew Dempsey

Uses ServoBlaster by Richard Hirst please see:
https://github.com/richardghirst/PiBits/tree/master/ServoBlaster

Hardware requirements:

For the airplane:

* A Raspberry Pi (RPI3, RPI2 and RPI Zero have been tested). Make sure Java & ServoBlaster are installed.
* A wireless internet connection (any mobile broadband modem or phone with wifi hotspot enabled will do)
* A battery to power the RPI.

For the groundstation:

* Any computer connected to the internet.
* Ensure Java is installed on the machine.
* Any gamepad connected to the computer is recommended but not essential.
* Currently the gamepad functionality is provided through keyboard emulation software, download your favourite keyboard emulator and refer to the instructions.

For the server:

* Any computer with Java installed will work fine.
* Turn on port forwarding to port 2000.
* You will need to know the public IP address of the server. The easiest way to find this is to go to https://whatismyipaddress.com/ and note the IPV4 number. You will need this number for the groundstation and the airplane software.
* The computer can be connected to the router via WiFi but it is recommended to connect via cable.

Setup:

1. Download the software and unzip. Copy Plane.jar and targetip.txt to the RPI you're using onboard the plane. Copy Transmitter.jar and targetip.txt to the machine you are using for the ground station. Copy Server.jar to the machine you are using for the server.

2. Edit the IP address in targetip.txt to reflect you own public IP address. Do this on both the plane and the ground station.

3. Set the Raspberry Pi onboard the plane to automatically launch the Plane.jar software. You can your favourite scheduling software to do this or you are using Raspbian OS, you can edit your rc.local file to auto launch the software. From your terminal use the command:

$ sudo nano /etc/rc.local

then add the line:

java -jar /home/pi/ICPlane/Plane.jar (change the location you reflect your own directory structure)

save and then reboot the system, it should now auto run the program ready for headless operation in the field.

Usage:

First start the server. It will display a message saying it is ready but the server can also be run headless. The server will wait indefinitely for connections so feel free to leave it running all day. Next, run the transmitter software. It will display a message that it connected to the server if there was no issues. Finally, turn on the Raspberry Pi. A typical Raspbian OS image will take no more than 30 seconds to load, now you are free to control your plane with the keyboard or with your joypad. 

Servo's & motor info:

The system currently supports two servos, one for your rudder on pin 9 and one for your elevator on pin 11. Optionally you can connect a motor to pin 15.

Keys:

W: Rudder 50%
A: Rudder -10%
D: Rudder +10%
Z: Rudder 10%
C: Rudder 90%

I: Elevator 50%
J: Elevator -10%
L: Elevator +10%
B: Elevator 10%
M: Elevator 90%

1 - 9: Motor 10% - 90%
0: Motor off
Spacebar (five times): Failsafe

*** Warnings Please read carefully before using this system ***

* Failsafe procedure - Press the spacebar five times in succession and the servos will return to center, the motor will turn off and the system will shut down.

* At present, the software and hardware setup do not emulate RxTx one to one so please be careful flying your planes. Ensure the safety of the public and yourself and respect public and private property at all times. Use this software at your own risk.

Tips:

* Try to make your hardware inside the plane as light as possible. Use the lightest battery you can find to power the RPI. I recommend using the RPI Zero because using the more powerful models offers no extra performance and you also gain the advantage of lower weight and smaller power requirements.

* I recommend first trying a glider configuration to get a feel for how the system handels.

Notes:
* A mobile app version of the transmitter software is under development.

* Contact
Email me (andrewdempsey2016@gmail.com) with suggestions and flight reports. Your feedback is very valuable to me and will help me develop the system further.
