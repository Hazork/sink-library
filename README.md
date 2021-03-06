<br />
<div align="center">
  <pre>
  <br />
  <h1>💧⚡🌊
Sink Library</h1>
  <br />
  </pre>
  <br />
  <br />
  <code
    ><a href="https://github.com/ArthurFiorette/sink-library/network/members"
      ><img
        src="https://img.shields.io/github/forks/ArthurFiorette/sink-library?logo=github&label=Forks"
        target="_blank"
        alt="Forks" /></a
  ></code>
  <code
    ><a href="https://github.com/ArthurFiorette/sink-library/issues"
      ><img
        src="https://img.shields.io/github/issues/ArthurFiorette/sink-library?logo=github&label=Issues"
        target="_blank"
        alt="Issues" /></a
  ></code>
  <code
    ><a href="https://github.com/ArthurFiorette/sink-library/stargazers"
      ><img
        src="https://img.shields.io/github/stars/ArthurFiorette/sink-library?logo=github&label=Stars"
        target="_blank"
        alt="Stars" /></a
  ></code>
  <code
    ><a href="https://github.com/ArthurFiorette/sink-library/blob/main/LICENSE"
      ><img
        src="https://img.shields.io/github/license/ArthurFiorette/sink-library?logo=githu&label=License"
        target="_blank"
        alt="License" /></a
  ></code>
  <code
    ><a href="https://jitpack.io/#ArthurFiorette/sink-library"
      ><img
        src="https://jitpack.io/v/ArthurFiorette/sink-library.svg"
        target="_blank"
        alt="Jitpack" /></a
  ></code>
</div>

#

<br />
<br />

#### `Sink-library` is a powerful and high-performance tool for building spigot plugins.

<br />

```java
// /docs/examples/ReadmeExample.java

public class ReadmeExample extends SinkPlugin {
  @Override
  protected ComponentLoader[] components() {
    return new ComponentLoader[] { () -> new MyListener(this) };
  }

  ...
}

// A simple class to be our listener
class MyListener extends SinkListener {

  public MyListener(BasePlugin owner) {
    super(owner);
  }

  @Override
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    // On every player join event, get the player and send a message
    event.getPlayer().sendMessage("Hello World!");
  }
}
```

<br />
<br />

### Installing

This library is hosted by **[JitPack](https://jitpack.io/#ArthurFiorette/sink-library)**.

> Replace `VERSION` with the latest [Github](https://github.com/ArthurFiorette/sink-library/releases) release.

```xml
<!-- Maven -->
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.github.ArthurFiorette</groupId>
    <artifactId>sink-library</artifactId>
    <version>VERSION</version>
  </dependency>
</dependencies>
```

```gradle
// Gradle
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}

dependencies {
  implementation 'com.github.ArthurFiorette:sink-library:VERSION'
}
```

```sbt
// Sbt
resolvers += "jitpack" at "https://jitpack.io"

libraryDependencies += "com.github.ArthurFiorette" % "sink-library" % "VERSION"
```

<br />

### Documentation

While the documentation is not done, you can look at the [examples](/docs/examples) and the **inline documentation** present.

<br />

### Commons problems

#### `IllegalComponentException`

This exception is thrown when you try to register a component or service, in your class that extends SinkPlugin, which is generic. Since the ComponentManager manages its objects with the key being their classes, two classes with different generic types would be recognized as the same component.

Here is an example of how to solve this problem with this simple component

```java
public class MyGenericClass<T> implements BaseComponent {
  ...
}
```

Create a new class that extends the designed class with the correct type

```java
public class MyStringClass extends MyGenericClass<String>  {}
```

And then you can register the `MyStringClass` normally.

<br />

### License

Licensed under the **MIT**. See [`LICENSE`](LICENSE) for more informations.

<br />

### Contact

See my contact information on my [github profile](https://github.com/ArthurFiorette) or open a new issue.

<br />
