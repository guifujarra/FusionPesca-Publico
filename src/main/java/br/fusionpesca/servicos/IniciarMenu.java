package br.fusionpesca.servicos;

import br.fusionpesca.gui.MenuPrincipal;
import org.bukkit.entity.Player;

public class IniciarMenu implements ServicoPesca{

    @Override
    public void executar(Player p) {
        somAbrirMenu(p);
        new MenuPrincipal(p);

    }
}
