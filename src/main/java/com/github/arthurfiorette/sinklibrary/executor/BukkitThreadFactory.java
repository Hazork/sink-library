package com.github.arthurfiorette.sinklibrary.executor;

import com.github.arthurfiorette.sinklibrary.core.BasePlugin;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseComponent;
import java.util.concurrent.ThreadFactory;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Every created thread from this object runs with the specified
 * {@link TaskContext}.
 * <p>
 * An object that creates new threads on demand. Using thread factories removes
 * hardwiring of calls to {@link Thread#Thread(Runnable) new Thread}, enabling
 * applications to use special thread subclasses, priorities, etc.
 * <p>
 * The {@link BukkitExecutor#asyncThreadFactory(BasePlugin)} or
 * {@link BukkitExecutor#syncThreadFactory(BasePlugin)} method provides a more
 * useful simple implementation, that sets the created thread context to known
 * values before returning it.
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
@RequiredArgsConstructor
public class BukkitThreadFactory implements ThreadFactory, BaseComponent {

  @Getter
  @NonNull
  private final BasePlugin basePlugin;

  @Getter
  @NonNull
  private final TaskContext context;

  @Override
  public Thread newThread(final Runnable runnable) {
    final Thread thread = new Thread(
      () -> {
        try {
          this.context.run(this.basePlugin, runnable);
        } catch (final Throwable t) {
          this.basePlugin.treatThrowable(
              this.getClass(),
              t,
              "Catched exception while running this thread."
            );
        }
      }
    );
    thread.setDaemon(false);
    thread.setName(this.getClass().getSimpleName() + "> " + runnable.getClass().getSimpleName());
    return thread;
  }
}
