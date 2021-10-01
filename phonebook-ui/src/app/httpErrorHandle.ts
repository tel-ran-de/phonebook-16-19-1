import {HttpErrorResponse} from "@angular/common/http";

export function httpErrorHandler(error: HttpErrorResponse): string {
  if (error.error.message)
    return error.error.message;
  else if (error.status === 500)
    return 'Server error, please try again later';
  else if (error.status === 504)
    return 'Server not available';
  else
    return 'Error, please try again later';
}
