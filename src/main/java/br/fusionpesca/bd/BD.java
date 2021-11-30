package br.fusionpesca.bd;

import br.fusionpesca.Main;
import br.fusionpesca.gerenciamento.Gerenciamento;
import br.fusionpesca.pescador.Pescador;
import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BD {

    private static final ConfigurationSection DB_SECTION = Main.plugin.getConfig().getConfigurationSection("MongoDB");
    private static final String HOST = DB_SECTION.getString("host");
    private static final int PORTA = DB_SECTION.getInt("porta");
    private static final String BANCO = DB_SECTION.getString("banco");
    private static final String USUARIO = DB_SECTION.getString("usuario");
    private static final String SENHA = DB_SECTION.getString("senha");

    private ServerAddress serverAddress;
    private MongoDatabase database;

    public BD() {
        abrirConexao();
    }

    private void abrirConexao() {
        try {
            serverAddress = new ServerAddress(HOST, PORTA);
            MongoCredential mongoCredential = MongoCredential.createCredential(USUARIO, BANCO, SENHA.toCharArray());
            MongoClient mongoClient = MongoClients.create(MongoClientSettings.builder()
                    .applyToClusterSettings(builder ->
                            builder.hosts(Arrays.asList(serverAddress)))
                    .credential(mongoCredential)
                    .build());
            database = mongoClient.getDatabase(BANCO);
            System.out.println("Conexão com o mongoDB bem sucedida!");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao abrir conexao com o mongoDB");
            System.out.println("O plugin esta sendo desabilitado");
            Bukkit.getServer().getPluginManager().disablePlugin(Main.plugin);
            e.printStackTrace();
        }
    }

    public void alterarVaraAtual(Pescador p, String nomeVara){
        MongoCollection<Document> pescadores = database.getCollection("pescadores");

        Document buscar = new Document("nome", p.getNome());
        pescadores.findOneAndUpdate(buscar, Updates.set("varaAtual", nomeVara));
    }

    public void adicionarVara(Pescador p, String nomeVara){
        MongoCollection<Document> pescadores = database.getCollection("pescadores");

        Document buscar = new Document("nome", p.getNome());
        pescadores.findOneAndUpdate(buscar, Updates.addToSet("varas", nomeVara));

    }

    public void criarPescador(Pescador p){
        Document doc = new Document("uuid", p.getId().toString())
                .append("nome", p.getNome())
                .append("varaAtual", p.getVaraAtual())
                .append("varas", p.getVaras());
        MongoCollection<Document> pescadores = database.getCollection("pescadores");
        pescadores.insertOne(doc);

    }


    public Pescador recuperarPescador(Player p) {
        if (Gerenciamento.isPescador(p)) {
            return Gerenciamento.getPescador(p);
        }

        MongoCollection<Document> pescadores = database.getCollection("pescadores");

        Document resultado = pescadores.find(Filters.eq("uuid", p.getUniqueId().toString())).first();
        if (resultado == null) {
            List<String> varas = new ArrayList<>();
            varas.add("default");
            Pescador pescador = new Pescador(p.getUniqueId(), p.getName(), varas, "default");
            criarPescador(pescador);
            return pescador;
        }
        List<String> varas = resultado.getList("varas", String.class);
        String varaAtual = resultado.getString("varaAtual");

        return new Pescador(p.getUniqueId(), p.getName(), varas, varaAtual);
    }

}
