package info.atende.nserver.service.interceptors;

import info.atende.nserver.dto.GeralConfig;
import info.atende.nserver.dto.RestResponse;
import info.atende.nserver.entity.Statistics;
import info.atende.nserver.entity.TipoNotificacao;
import info.atende.nserver.exceptions.EmailNotSendedException;
import info.atende.nserver.model.CrudDAO;
import info.atende.nserver.service.NotificationServiceInterface;
import info.atende.webutil.jpa.Config;
import info.atende.webutil.jpa.ConfigUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.security.Principal;
import java.util.Optional;
import java.util.logging.Logger;


/**
 * TODO Implement for Spring Something similar
 * Criado por Giovanni Silva <giovanni@pucminas.br>
 */
public class NotificationStatisticDecorator  {
    NotificationServiceInterface notificationServiceInterface;

    @EJB
    CrudDAO crudDAO;

    private static Logger logger = Logger.getLogger(NotificationStatisticDecorator.class.getName());

    public ResponseEntity<String> sendEmail(@NotBlank  String email, @NotBlank
                                  String subject, @NotBlank  String message, boolean html,
                              String token,
                              HttpServletRequest hsr) throws EmailNotSendedException {
                                      logger.fine("Enviando email para " + email);
                                      Principal userPrincipal = hsr.getUserPrincipal();
                                      ResponseEntity<String> response = notificationServiceInterface.sendEmail(email, subject, message, html, token, hsr);;
                                      /**
                                       * Tenta enviar o email, caso tenha sucesso e usuário esteja logado. Salva estatisticas
                                       */
                                      if(userPrincipal != null){
                                          if(response.getStatusCode() == HttpStatus.OK && statisticEnabled()){
                                              Statistics statistics = new Statistics(userPrincipal.getName(), TipoNotificacao.EMAIL);
                                              crudDAO.save(statistics);
                                          }

                                      }
                                      return response;
                                  }

    public RestResponse sendEmailTest(@Email @NotBlank String email,
                                     HttpServletRequest hsr) throws EmailNotSendedException {
        logger.fine("Enviando email de teste para " + email);
        Principal userPrincipal = hsr.getUserPrincipal();

        /**
         * Tenta enviar o email, caso tenha sucesso e usuário esteja logado. Salva estatisticas
         */
        RestResponse response = notificationServiceInterface.sendEmailTest(email, hsr);
        if(userPrincipal != null){
            if(response.getSuccess() && statisticEnabled()){
                Statistics statistics = new Statistics(userPrincipal.getName(), TipoNotificacao.EMAIL_TEST);
                crudDAO.save(statistics);
            }

        }

        return response;

    }
    private boolean statisticEnabled(){
        Config conf = crudDAO.find(Config.class, GeralConfig.CONFIG_NAME);
        if(conf != null){
            Optional<GeralConfig> geral = ConfigUtils.parseConfig(conf, GeralConfig.class);
            return geral.get().getHabilitarEstatisticas();
        }else{
            return false;
        }
    }
}
