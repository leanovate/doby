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


## Description

Developers like to write TODOs in the code to describe a possible amelioration:

```scala
// TODO: check the mime-type
```

As the project evolves, the number of TODOs typically grows up and nobody take care of them seriously.

With DoBy, a TODO must have an expiration date:

- Before this date, the program compiles and runs as usual.

- Three weeks before this date, the compiler will emit a warning about the note.

- After this date, the program does not compile anymore. You **must** do something about this TODO.


## Usage

### Add DoBy as dependency

To use this library, add it as [dependency](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22doby_2.11%22).

```scala
libraryDependencies += "de.leanovate.doby" %% "doby" % "0.6" % "provided"
```
DoBy is only compatible with Scala 2.11.

### Add an import

```scala
import de.leanovate.doby._
```

### Write your TODO

A TODO note must indicate the author, a description and an expiration date.

The expiration date can be written like `2011/05/25` or like `2011-05-25`

```scala
TODO("YaSi", "do not write any TODO anymore", "2011/05/25")
```

You can also use the general expire. For info, see the [example](sample/src/test/scala/de/leanovate/doby/ExpirationApp.scala)

## Changelog
### version 0.6

Change the default package from `de.leanovate.doby.Expiration._` to `de.leanovate.doby._`


## FAQ

### Difference with [do_by in in Ruby](https://github.com/andyw8/do_by)
 
do_by (in ruby) throws an exception at runtime when a note is expired.

It means that an expired note will break a running program.

With DoBy (in Scala), an expired note does not have any effect on a running program. Instead, at compile time, the compiler will warn or output an error if a note is about to expire or has expired. A TODO note is written by a developer and must be take care by a developer.
 


## Developer info

Travis: [![Build Status](https://travis-ci.org/leanovate/doby.png?branch=master)](https://travis-ci.org/leanovate/doby)


## Alternatives

- [FIXME](https://github.com/tysonjh/fixme)


## Credits
- [do_by: Automatically expiring TODO notes in Ruby](https://github.com/andyw8/do_by)

- [Idea from @brikis98 to implement expiring TODOs with scala macros](https://twitter.com/brikis98/status/467924891837030400): "TODO's that throw exceptions when they expire. Neat, but I want compile, not runtime errors. Doable w/ scala macros?"

