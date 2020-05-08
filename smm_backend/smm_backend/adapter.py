from django.conf import settings
from allauth.account.adapter import DefaultAccountAdapter
from rest_framework_simplejwt.tokens import RefreshToken
from django.contrib.auth.models import User
import traceback

class MyAccountAdapter(DefaultAccountAdapter):

    def get_login_redirect_url(self, request):
        path = "/rest-auth/user/{id}/{username}/{fn}/{ln}/{t}/{tr}"
        tr = RefreshToken.for_user(request.user)
        token_ref = str(tr)
        token_acc = str(tr.access_token)
        return path.format(id=request.user.id,username=request.user.username,fn=request.user.first_name,ln=request.user.last_name, t = token_acc, tr = token_ref)


