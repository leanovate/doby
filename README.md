DoBy
====

Scala implementation of expiring TODO notes.

Original idea from [do_by: Automatically expiring TODO notes in Ruby](https://github.com/andyw8/do_by)

## tl;dr

Use this library if you want to write TODO notes with an expiration date.

An expired TODO will not compiled anymore.

*Example:*

```scala
TODO("YaSi", "do not write any TODO anymore", "2011/05/25")
```
does not compile.

## usage

To use this library, add it as dependency.

```scala
libraryDependencies += "de.leanovate" % "doby" % "0.3.0"
```


### the current problem

Developers like to write TODOs in the code to describe a possible amelioration:

```scala
// TODO: check the mime-type
```

As the project evolves, the number of TODOs typically grows up.

With DoBy, a TODO must have an expiration date.

Before this date, the program compiles and runs as usual.

After this date, the program does not compile anymore. You **must** do something about this TODO.

[Example](sample/src/test/scala/de/leanovate/doby/ExpirationApp.scala)

## FAQ

### Difference with [do_by in in Ruby](https://github.com/andyw8/do_by)
 
do_by (in ruby) throws an exception at runtime when a note is expired.

It means that n expired note will break a running program.

With DoBy (in Scala), a TODO note is written by a developer and must be take care by a developer.
 
That's why DoBy operates at compile time rather that at runtime.
 

## Developer info

[Continuous Integration](https://travis-ci.org/leanovate/doby)


## Credits
[do_by: Automatically expiring TODO notes in Ruby](https://github.com/andyw8/do_by)

[Idea from @brikis98 to implement expiring TODOs with scala macros](https://twitter.com/brikis98/status/467924891837030400): "TODO's that throw exceptions when they expire. Neat, but I want compile, not runtime errors. Doable w/ scala macros?"

