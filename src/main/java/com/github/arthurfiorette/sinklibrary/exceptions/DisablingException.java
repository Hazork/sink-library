package com.github.arthurfiorette.sinklibrary.exceptions;

import com.github.arthurfiorette.sinklibrary.interfaces.BaseService;
import lombok.NoArgsConstructor;

/**
 * A simple class to wrap any exception thrown while disabling a
 * {@link BaseService}
 */
@NoArgsConstructor
public class DisablingException extends RuntimeException {

  private static final long serialVersionUID = -4764138856100635058L;

  public DisablingException(final String message) {
    super(message);
  }

  public DisablingException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public DisablingException(final Throwable cause) {
    super(cause);
  }

  protected DisablingException(
    final String message,
    final Throwable cause,
    final boolean enableSuppression,
    final boolean writableStackTrace
  ) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  @Override
  public String toString() {
    final String s = this.getCause().getClass().getName();
    final String message = this.getLocalizedMessage();
    return (message != null) ? (s + ": " + message) : s;
  }
}
