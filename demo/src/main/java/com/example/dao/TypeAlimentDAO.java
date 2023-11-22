// Instructions d'importation pour les classes Java nécessaires et les classes externes
package com.example.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.util.ResultSetTableDisplay;
import com.example.util.ServletUtils;

import javax.json.JsonObject;

// Définition de la classe TypeAlimentDAO implémentant l'interface IGenericCRUD
public class TypeAlimentDAO implements IGenericCRUD {

    // Instance de DatabaseConnection pour gérer les connexions à la base de données
    private DatabaseConnection dbConnection;

    // Constructeur prenant une instance de DatabaseConnection
    public TypeAlimentDAO(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // Méthode pour traiter les requêtes GET pour une représentation HTML des données
    public void handleGetHTML(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        // Établir une connexion à la base de données
        dbConnection.connect();

        // Définir le type de contenu de la réponse en HTML
        response.setContentType("text/html");

        // Obtenir l'objet PrintWriter pour écrire la réponse
        PrintWriter out = response.getWriter();

        // Afficher un tableau HTML de tous les enregistrements TypeAliment
        ResultSetTableDisplay.displayHtmlTable(listAllTypeAliment(), out);

        // Convertir le ResultSet en format de tableau HTML
        String typeAlimentHtml = ResultSetTableDisplay.toHtmlTable(listAllTypeAliment());
        System.out.println(typeAlimentHtml);

        // Se déconnecter de la base de données
        dbConnection.disconnect();
    }

    // Implémentation de la méthode handleGet pour traiter les requêtes GET
    @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        try {
            // Établir une connexion à la base de données
            dbConnection.connect();

            // Récupérer tous les enregistrements TypeAliment en tant que ResultSet
            ResultSet resultSet = listAllTypeAliment();

            // Convertir le ResultSet en format JSON
            String jsonResponse = ServletUtils.convertResultSetToJson(resultSet);

            // Envoyer la réponse JSON
            ServletUtils.sendJsonResponse(response, jsonResponse);

            // Fermer le ResultSet et se déconnecter de la base de données
            resultSet.close();
            dbConnection.disconnect();
        } catch (SQLException ex) {
            // Gérer les exceptions SQL
            ServletUtils.handleSqlException(response, ex);
        } catch (Exception ex) {
            // Gérer les autres exceptions
            ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur serveur : " + ex.getMessage());
        }     
    }

    // Implémentation de la méthode handlePost pour traiter les requêtes POST
    @Override
    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        try {
            // Analyser la requête JSON à partir de HttpServletRequest
            JsonObject jsonObject = ServletUtils.parseJsonRequest(request);

            // Extraire les valeurs de l'objet JSON
            String nom = jsonObject.getString("nom", "");

            // Valider les données de la requête
            ServletUtils.validateRequestData(jsonObject, "nom");

            // Insérer un nouvel enregistrement TypeAliment et récupérer le résultat en tant que ResultSet
            ResultSet resultSet = insertTypeAlimentAndGet(nom);

            // Convertir le ResultSet en format JSON
            String jsonResponse = ServletUtils.convertResultSetToJson(resultSet);

            // Envoyer la réponse JSON
            ServletUtils.sendJsonResponse(response, jsonResponse);
        } catch (SQLException ex) {
            // Gérer les exceptions SQL
            ServletUtils.handleSqlException(response, ex);
        } catch (IOException ex) {
            // Gérer les exceptions IO
            ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        } catch (Exception ex) {
            // Gérer les autres exceptions
            ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de l'insertion de type_aliment : " + ex.getMessage());
        }
    }

    // Implémentation de la méthode handlePut pour traiter les requêtes PUT
    @Override
    public void handlePut(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        try {
            // Analyser la requête JSON à partir de HttpServletRequest
            JsonObject jsonObject = ServletUtils.parseJsonRequest(request);

            // Extraire les valeurs de l'objet JSON
            int id = jsonObject.getInt("id");
            String nomTypeAliment = jsonObject.getString("nom", "");

            // Vérifier les champs requis manquants
            if (nomTypeAliment.isEmpty()) {
                ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Champs requis manquants");
                return;
            }

            // Mettre à jour un enregistrement TypeAliment existant
            boolean updated = updateTypeAliment(id, nomTypeAliment);

            // Envoyer une réponse appropriée en fonction du résultat de la mise à jour
            if (updated) {
                ServletUtils.sendJsonResponse(response, "{\"message\":\"TypeAliment mis à jour avec succès.\"}");
            } else {
                ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_NOT_FOUND, "TypeAliment non trouvé ou non mis à jour");
            }
        } catch (NumberFormatException ex) {
            // Gérer NumberFormatException
            ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Format invalide pour l'ID");
        } catch (SQLException ex) {
            // Gérer les exceptions SQL
            ServletUtils.handleSqlException(response, ex);
        } catch (Exception ex) {
            // Gérer les autres exceptions
            ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    // Implémentation de la méthode handleDelete pour traiter les requêtes DELETE
    @Override
    public void handleDelete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        try {
            // Analyser la requête JSON à partir de HttpServletRequest
            JsonObject jsonObject = ServletUtils.parseJsonRequest(request);

            // Extraire les valeurs de l'objet JSON
            int id = jsonObject.getInt("id");

            // Supprimer un enregistrement TypeAliment existant
            boolean deleted = deleteTypeAliment(id);

            // Envoyer une réponse appropriée en fonction du résultat de la suppression
            if (deleted) {
                ServletUtils.sendJsonResponse(response, "{\"message\": \"TypeAliment supprimé avec succès.\"}");
            } else {
                ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_NOT_FOUND, "TypeAliment non trouvé ou non supprimé");
            }
        } catch (NumberFormatException ex) {
            // Gérer NumberFormatException
            ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Format invalide pour l'ID");
        } catch (SQLException ex) {
            // Gérer les exceptions SQL
            ServletUtils.handleSqlException(response, ex);
        } catch (Exception ex) {
            // Gérer les autres exceptions
            ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur serveur : " + ex.getMessage());
        }
    }

    // Implémentation de la méthode handleFindById pour trouver un TypeAliment par ID
    @Override
    public void handleFindById (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        try {
            // Analyser le paramètre ID de HttpServletRequest
            int id = Integer.parseInt(request.getParameter("id"));

            // Trouver un TypeAliment par ID et récupérer le résultat en tant que ResultSet
            ResultSet resultSet = findById(id);

            // Convertir le ResultSet en format JSON
            String jsonResponse = ServletUtils.convertResultSetToJson(resultSet);
            ServletUtils.sendJsonResponse(response, jsonResponse);

            // Fermer le ResultSet et se déconnecter de la base de données
            resultSet.close();
            dbConnection.disconnect();
        } catch (NumberFormatException ex) {
            // Gérer NumberFormatException
            ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Format invalide pour l'ID");
        } catch (SQLException ex) {
            // Gérer les exceptions SQL
            ServletUtils.handleSqlException(response, ex);
        } catch (Exception ex) {
            // Gérer les autres exceptions
            ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur serveur : " + ex.getMessage());
        }
    }

    // Implémentation de la méthode handleFindByName pour trouver un TypeAliment par nom
    @Override
    public void handleFindByName(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        try {
            // Analyser le paramètre de nom de HttpServletRequest
            String nom = request.getParameter("nom");
            ResultSet resultSet = findByName(nom);

            // Convertir le ResultSet en format JSON
            String jsonResponse = ServletUtils.convertResultSetToJson(resultSet);
            ServletUtils.sendJsonResponse(response, jsonResponse);

            // Fermer le ResultSet et se déconnecter de la base de données
            resultSet.close();
            dbConnection.disconnect();
        } catch (SQLException ex) {
            // Gérer les exceptions SQL
            ServletUtils.handleSqlException(response, ex);
        } catch (Exception ex) {
            // Gérer les autres exceptions
            ServletUtils.sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur serveur : " + ex.getMessage());
        }
    }

    // Méthode pour insérer un nouveau TypeAliment avec le nom spécifié dans la base de données
    public boolean insertTypeAliment(String nom) throws SQLException {
        String sql = "INSERT INTO type_aliment (nom) VALUES (?)";
        dbConnection.connect();
        PreparedStatement statement = dbConnection.getJdbcConnection().prepareStatement(sql);

        statement.setString(1, nom);

        boolean result = statement.executeUpdate() > 0;

        statement.close();
        dbConnection.disconnect();

        return result;
    }

    // Méthode pour insérer un nouveau TypeAliment avec le nom spécifié dans la base de données et récupérer le résultat en tant que ResultSet
    public ResultSet insertTypeAlimentAndGet(String nom) throws SQLException {
        String insertSql = "INSERT INTO type_aliment (nom) VALUES (?)";
        String selectSql = "SELECT * FROM type_aliment WHERE id = ?";

        dbConnection.connect();

        PreparedStatement insertStatement = dbConnection.getJdbcConnection().prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
        insertStatement.setString(1, nom);
        insertStatement.executeUpdate();

        ResultSet generatedKeys = insertStatement.getGeneratedKeys();
        if (!generatedKeys.next()) {
            throw new SQLException("Création de type_aliment échouée, aucun ID obtenu");
        }
        int newTypeAlimentId = generatedKeys.getInt(1);
        insertStatement.close();

        PreparedStatement selectStatement = dbConnection.getJdbcConnection().prepareStatement(selectSql);
        selectStatement.setInt(1, newTypeAlimentId);
        ResultSet resultSet = selectStatement.executeQuery();

        return resultSet;
    }

    // Méthode pour récupérer tous les enregistrements TypeAliment de la base de données
    public ResultSet listAllTypeAliment() throws SQLException {
        String sql = "SELECT * FROM type_aliment";
        dbConnection.connect();

        Statement statement = dbConnection.getJdbcConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        return resultSet;
    }

    // Méthode pour mettre à jour le nom d'un TypeAliment avec l'ID spécifié dans la base de données
    public boolean updateTypeAliment(int id, String nom) throws SQLException {
        String sql = "UPDATE type_aliment SET nom = ? WHERE id = ?";
        dbConnection.connect();

        PreparedStatement statement = dbConnection.getJdbcConnection().prepareStatement(sql);
        statement.setString(1, nom);
        statement.setInt(2, id);

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        dbConnection.disconnect();
        return rowUpdated;
    }

   // Méthode pour supprimer un TypeAliment de la base de données en utilisant son ID
public boolean deleteTypeAliment(int id) throws SQLException {
    // Requête SQL pour supprimer le TypeAliment avec l'ID spécifié
    String sql = "DELETE FROM type_aliment WHERE id = ?";
    
    // Établir une connexion à la base de données
    dbConnection.connect();

    // Préparer la déclaration SQL avec le paramètre ID
    PreparedStatement statement = dbConnection.getJdbcConnection().prepareStatement(sql);
    statement.setInt(1, id);

    // Exécuter la requête et vérifier si une ligne a été supprimée
    boolean rowDeleted = statement.executeUpdate() > 0;

    // Fermer la déclaration et se déconnecter de la base de données
    statement.close();
    dbConnection.disconnect();

    // Retourner true si une ligne a été supprimée, sinon false
    return rowDeleted;
}

// Méthode pour obtenir le dernier TypeAliment inséré dans la base de données
public ResultSet getLastInsertedTypeAliment() throws SQLException {
    // Requête SQL pour sélectionner le dernier TypeAliment inséré
    String sql = "SELECT * FROM type_aliment ORDER BY id DESC LIMIT 1";

    // Créer une déclaration pour exécuter la requête
    Statement statement = dbConnection.getJdbcConnection().createStatement();

    // Exécuter la requête et obtenir le résultat sous forme de ResultSet
    ResultSet resultSet = statement.executeQuery(sql);

    // Retourner le ResultSet contenant le dernier TypeAliment inséré
    return resultSet;
}

// Méthode pour trouver un TypeAliment par son ID dans la base de données
public ResultSet findById(int id) throws SQLException {
    // Requête SQL pour sélectionner un TypeAliment par son ID
    String sql = "SELECT * FROM type_aliment WHERE id = ?";
    
    // Établir une connexion à la base de données
    dbConnection.connect();

    // Préparer la déclaration SQL avec le paramètre ID
    PreparedStatement statement = dbConnection.getJdbcConnection().prepareStatement(sql);
    statement.setInt(1, id);

    // Exécuter la requête et obtenir le résultat sous forme de ResultSet
    ResultSet resultSet = statement.executeQuery();

    // Retourner le ResultSet contenant le TypeAliment trouvé
    return resultSet;
}

// Méthode pour trouver un TypeAliment par son nom dans la base de données
public ResultSet findByName(String nom) throws SQLException {
    // Requête SQL pour sélectionner un TypeAliment par son nom
    String sql = "SELECT * FROM type_aliment WHERE nom = ?";
    
    // Établir une connexion à la base de données
    dbConnection.connect();

    // Préparer la déclaration SQL avec le paramètre nom
    PreparedStatement statement = dbConnection.getJdbcConnection().prepareStatement(sql);
    statement.setString(1, nom);

    // Exécuter la requête et obtenir le résultat sous forme de ResultSet
    ResultSet resultSet = statement.executeQuery();

    // Retourner le ResultSet contenant le TypeAliment trouvé par nom
    return resultSet;
}
}
