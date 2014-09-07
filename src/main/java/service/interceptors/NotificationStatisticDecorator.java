package service.interceptors;

import dto.GeralConfig;
import dto.RestResponse;
import entity.Statistics;
import entity.TipoNotificacao;
import info.atende.exceptions.EmailNotSendedException;
import info.atende.webutil.jpa.Config;
import info.atende.webutil.jpa.ConfigUtils;
import model.CrudEJB;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import service.NotificationServiceInterface;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import java.security.Principal;
import java.util.Optional;
import java.util.logging.Logger;


/**
 * Criado por Giovanni Silva <giovanni@pucminas.br>
 */
@Decorator
public class NotificationStatisticDecorator implements NotificationServiceInterface {
    @Inject
    @Delegate
    NotificationServiceInterface notificationServiceInterface;

    @EJB
    CrudEJB crudEJB;

    private static Logger logger = Logger.getLogger(NotificationStatisticDecorator.class.getName());

    @Override
    public RestResponse sendEmail(@NotBlank @FormParam("email") String email, @NotBlank @FormParam("subject")
                                  String subject, @NotBlank @FormParam("message") String message, @FormParam("html") boolean html,
                                  @Context HttpServletRequest hsr) throws EmailNotSendedException {
                                      logger.fine("Enviando email para " + email);
                                      Principal userPrincipal = hsr.getUserPrincipal();
                                      RestResponse response = notificationServiceInterface.sendEmail(email, subject, message, html, hsr);;
                                      /**
                                       * Tenta enviar o email, caso tenha sucesso e usuário esteja logado. Salva estatisticas
                                       */
                                      if(userPrincipal != null){
                                          if(response.getSuccess() && statisticEnabled()){
                                              Statistics statistics = new Statistics(userPrincipal.getName(), TipoNotificacao.EMAIL);
                                              crudEJB.save(statistics);
                                          }

                                      }
                                      return response;
                                  }

    @Override
    public RestResponse sendEmailTest(@Email @NotBlank String email,
                                      @Context HttpServletRequest hsr) throws EmailNotSendedException {
        logger.fine("Enviando email de teste para " + email);
        Principal userPrincipal = hsr.getUserPrincipal();

        /**
         * Tenta enviar o email, caso tenha sucesso e usuário esteja logado. Salva estatisticas
         */
        RestResponse response = notificationServiceInterface.sendEmailTest(email, hsr);
        if(userPrincipal != null){
            if(response.getSuccess() && statisticEnabled()){
                Statistics statistics = new Statistics(userPrincipal.getName(), TipoNotificacao.EMAIL_TEST);
                crudEJB.save(statistics);
            }

        }

        return response;

    }
    private boolean statisticEnabled(){
        Config conf = crudEJB.find(Config.class, GeralConfig.CONFIG_NAME);
        if(conf != null){
            Optional<GeralConfig> geral = ConfigUtils.parseConfig(conf, GeralConfig.class);
            return geral.get().getHabilitarEstatisticas();
        }else{
            return false;
        }
    }
}
