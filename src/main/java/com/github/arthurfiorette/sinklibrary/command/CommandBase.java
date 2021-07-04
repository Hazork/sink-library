package com.github.arthurfiorette.sinklibrary.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;

import com.github.arthurfiorette.sinklibrary.core.SinkPlugin;
import com.github.arthurfiorette.sinklibrary.interfaces.BaseService;
import com.google.common.base.Verify;
import com.google.common.base.VerifyException;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * This class is a way to create commands
 *
 * @author https://github.com/ArthurFiorette/sink-library/
 */
public final class CommandBase implements TabExecutor, BaseService {

  @Getter
  private final String name;

  @Getter
  private final SinkPlugin basePlugin;

  private final Map<String, Argument> commandMap = new HashMap<>();

  @Getter
  @Setter
  @NonNull
  private Argument defaultArgument = null;

  @Getter
  @Setter
  private String unknownMessage = "§7Unknown argument.";

  /**
   * Constructs a command base
   *
   * @param plugin the sink plugin instance
   * @param name the command name
   */
  public CommandBase(final SinkPlugin plugin, final String name) {
    this.basePlugin = plugin;
    this.name = name;
  }

  /**
   * {@inheritDoc}
   *
   * @throws VerifyException if the command isn't found. (ensure that it is
   * registered in plugin.yml}
   */
  @Override
  public void enable() {
    final PluginCommand command = this.basePlugin.getCommand(this.name);
    Verify.verifyNotNull(command, "The command %s wasn't found", this.name);
    command.setExecutor(this);
    command.setTabCompleter(this);
  }

  @Override
  public void disable() {
    final PluginCommand command = this.basePlugin.getCommand(this.name);
    command.setExecutor(null);
    command.setTabCompleter(null);
  }

  @Override
  public boolean onCommand(final CommandSender sender, final Command command, final String alias, final String[] args) {
    if (args.length == 0) {
      this.defaultArgument.handle(sender, alias, Arrays.asList(args));
      return true;
    }
    final Argument cmd = this.commandMap.get(args[0]);
    if ((cmd != null) && cmd.test(sender)) {
      if ((cmd.getPermission() != null) && sender.hasPermission(cmd.getPermission())) {
        cmd.handle(sender, alias, CommandBase.removeFirst(args));
      } else {
        sender.sendMessage(cmd.getPermissionMessage());
      }
    } else {
      sender.sendMessage(this.unknownMessage);
    }
    return true;
  }

  @Override
  public List<String> onTabComplete(final CommandSender sender, final Command command, final String alias,
      final String[] args) {
    if (args.length == 0) {
      return new ArrayList<>(this.commandMap.keySet());
    } else if (this.commandMap.containsKey(args[0])) {
      return this.commandMap.get(args[0]).onTabComplete(sender, alias, CommandBase.removeFirst(args));
    } else {
      return null;
    }
  }

  /**
   * @param args all the new arguments to be added to this command
   */
  public void addArguments(final Argument... args) {
    for(final Argument arg: args) {
      this.commandMap.put(arg.getName(), arg);
      if (this.defaultArgument == null) {
        this.defaultArgument = arg;
      }
    }
  }

  private static List<String> removeFirst(final String[] str) {
    return Arrays.asList(Arrays.copyOfRange(str, 1, str.length));
  }
}
