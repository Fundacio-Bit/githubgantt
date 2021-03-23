package org.fundaciobit.githubgantt.back.utils;

import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 
 * @author anadal
 *
 */
public class GithubGanttMaxUploadSizeExceededException extends MaxUploadSizeExceededException {

  final String msgCode;

  /**
   * @param msg
   */
  public GithubGanttMaxUploadSizeExceededException(Throwable cause, long maxSize, String msgCode) {
    super(maxSize, cause);    
    this.msgCode = msgCode;
  }

  public String getMsgCode() {
    return msgCode;
  }

}
