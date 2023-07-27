# GiftCardGenerator

### Description
Project for www.vonavyobchod.cz

### Installation
**DEFAULT PROJECT**
1. Clone the repository
2. Add project to special `Directory`
3. Run project `^ + R` <br><br>

**.JAR APP (For IntelliJ IDEA)**
1. Clone the repository
2. File -> Project Structre `CMD + U,` -> Libraries -> ` + ` -> ` downloaded library ` -> JAR -> SELECT ` From modules with dependecies ` -> Main Class ` Select App.java ` -> OK -> OK
3. Build -> Build Artifacts -> Build
4. Copy .JAR file `.../GiftCardGenerator/out/artifacts` to new package `(e.g. .../Documents/GiftCardApp)`
5. Run .JAR app

### Controls
`PRICE` Accepts int numbers in range `<100-10000>`<br>
`DATE` Accepts Date with specific format `dd.mm.yyyy`<br>

### Files
1. List `ListGiftCards.csv` containig all necessary information about all generated GiftCards (editable)<br>
2. New GiftCard will be generated with unique name<br>

**Developed on Mac (M1) with JDK 19**

