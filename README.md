# WordTail

This plugin has the ability to force the "word-tail" of chats for players.

## Installation
If you want to use it for the time being, it works if you put the jar file in the `target` directory in the `plugins` directory.  
https://github.com/toy-kun/wordtail/blob/master/target/wordtail-1.0-SNAPSHOT.jar


However, it is recommended to build it for optimization.

## Usage 

* Get a list of registered words
```
/wtlist
```

* Register force word for specified player.
```
/wtset [player] [word]
ex) 
/wtset toy_kun にょ
```

It is able to register multiple words.
At this time, you can speak if it matches to any of the registered words.

The registered words managed by `config.yml`. See the following sample sentence. 

```yml
# config.yml

wordtail:
    toy_kun:
        - にょ
        - ざます
```

* Reset registered words.
```
/wtreset [player]
```

## Video tutorials
https://youtu.be/U_wf_U-854A (Japanese)
