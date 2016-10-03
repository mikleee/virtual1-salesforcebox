package com.virtual1.salesforcebox.sf;

import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.DeleteResult;
import com.sforce.soap.partner.DescribeSObjectResult;
import com.sforce.soap.partner.Error;
import com.sforce.soap.partner.Field;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.SaveResult;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
import com.sforce.ws.SessionRenewer;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Mikhail Tkachenko created on 11.08.16 10:21
 */
final class SalesforceDataSource {
    private final static Logger LOGGER = Logger.getLogger(SalesforceDataSource.class);

    private final String username;
    private final String password;
    private final String token;
    private final boolean sandbox;
    private final String clientIdentifier;

    private PartnerConnection connection;


    SalesforceDataSource(String username, String password, String token, boolean sandbox, String clientIdentifier) {
        this.username = username;
        this.password = password;
        this.token = token;
        this.sandbox = sandbox;
        this.clientIdentifier = clientIdentifier;
    }

    private final static class SessionRenewerImpl implements SessionRenewer {
        @Override
        public SessionRenewalHeader renewSession(ConnectorConfig connectorConfig) throws ConnectionException {
            LOGGER.trace("Salesforce session has been expired try to obtain a new one...");
            connectorConfig.setSessionId(null);
            PartnerConnection connection = Connector.newConnection(connectorConfig);
            SessionRenewalHeader header = new SessionRenewalHeader();
            header.name = new QName("urn:partner.soap.sforce.com", "SessionHeader");
            header.headerElement = connection.getSessionHeader();
            return header;
        }
    }

    PartnerConnection getConnection() {
        if (connection == null) {
            createConnection();
        }
        return connection;
    }

    private void createConnection() {
        try {
            ConnectorConfig config = createConnectorConfig();
            connection = Connector.newConnection(config);
            connection.setCallOptions(clientIdentifier, null);
        } catch (ConnectionException e) {
            throw new SalesforceException("Error on connecting to Salesforce.", e);
        }
    }

    private ConnectorConfig createConnectorConfig() {
        ConnectorConfig config = new ConnectorConfig();
        if (sandbox) {
            config.setAuthEndpoint("https://test.salesforce.com/services/Soap/u/29.0");
            config.setServiceEndpoint("https://test.salesforce.com/services/Soap/u/29.0");
        }

        config.setUsername(username);
        config.setPassword(password + StringUtils.defaultString(token));
        config.setSessionRenewer(new SessionRenewerImpl());
        return config;
    }


    List<SObject> retrieveAll(String queryString) {
        try {
            List<SObject> result = new ArrayList<>();
            QueryResult queryResult = getConnection().query(queryString);
            if (queryResult.getRecords() != null) {
                Collections.addAll(result, queryResult.getRecords());

                String queryLocator = queryResult.getQueryLocator();
                while (queryLocator != null) {
                    queryResult = getConnection().queryMore(queryLocator);
                    queryLocator = queryResult.getQueryLocator();
                    Collections.addAll(result, queryResult.getRecords());
                }
            }
            return result;
        } catch (ConnectionException e) {
            throw new SalesforceException("Error on retrieve all", e);
        }
    }


    String delete(String id) {
        try {
            DeleteResult result = getConnection().delete(new String[]{id})[0];
            validateResult(result);
            return result.getId();
        } catch (ConnectionException e) {
            throw new SalesforceException("Error on delete", e);
        }
    }

    List<String> delete(String[] ids) {
        try {
            DeleteResult[] results = getConnection().delete(ids);
            List<String> deleted = new ArrayList<>();
            for (DeleteResult result : results) {
                validateResult(result);
                deleted.add(result.getId());
            }
            return deleted;
        } catch (ConnectionException e) {
            throw new SalesforceException("Error on delete", e);
        }
    }


    String update(SObject sfObject) {
        try {
            SaveResult update = getConnection().update(new SObject[]{sfObject})[0];
            validateResult(update);
            return update.getId();
        } catch (ConnectionException e) {
            throw new SalesforceException("Error on update", e);
        }
    }

    List<String> update(SObject[] sfObjects) {
        try {
            List<String> ids = new ArrayList<>();
            SaveResult[] create = getConnection().update(sfObjects);
            for (SaveResult result : create) {
                validateResult(result);
                ids.add(result.getId());
            }
            return ids;
        } catch (ConnectionException e) {
            throw new SalesforceException("Error on update", e);
        }
    }

    String create(SObject sfObject) {
        try {
            SaveResult create = getConnection().create(new SObject[]{sfObject})[0];
            validateResult(create);
            return create.getId();
        } catch (ConnectionException e) {
            throw new SalesforceException("Error on create", e);
        }
    }

    List<String> create(SObject[] sfObjects) {
        try {
            List<String> ids = new ArrayList<>();
            SaveResult[] create = getConnection().create(sfObjects);
            for (SaveResult result : create) {
                validateResult(result);
                ids.add(result.getId());
            }
            return ids;
        } catch (ConnectionException e) {
            throw new SalesforceException("Error on create", e);
        }
    }

    Field[] describeSObject(String object) {
        try {
            DescribeSObjectResult describe = getConnection().describeSObject(object);
            return describe.getFields();
        } catch (ConnectionException e) {
            throw new SalesforceException("Error on describe object", e);
        }
    }

    private void validateResult(SaveResult result) {
        validateResult(result.getErrors());
    }

    private void validateResult(DeleteResult result) {
        validateResult(result.getErrors());
    }

    private void validateResult(com.sforce.soap.partner.Error[] errors) {
        if (errors.length > 0) {
            StringBuilder report = new StringBuilder();
            for (Error error : errors) {
                report.append(error.getStatusCode()).append(": ").append(error.getMessage()).append("; ");
            }
            throw new SalesforceException(report.toString());
        }
    }


}
