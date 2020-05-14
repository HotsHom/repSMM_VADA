from django.shortcuts import get_list_or_404, render
from django.http import HttpResponseRedirect
from .models import VkToken, Post, UserPost
from .serializers import TokenSerializer, PostSerializer, UserPostSerializer
from rest_framework.response import Response
from rest_framework.generics import get_object_or_404
from rest_framework import viewsets, generics
from django.db.migrations import serializer
from rest_framework_swagger import renderers
from rest_framework.schemas import SchemaGenerator
from rest_framework.views import APIView
import datetime

class TokenViewSet(viewsets.ModelViewSet):
    queryset = VkToken.objects.all()
    serializer_class = TokenSerializer

class PostViewSet(viewsets.ModelViewSet):
    queryset = Post.objects.all()
    serializer_class = PostSerializer

class UserPostViewSet(viewsets.ModelViewSet):
    queryset = UserPost.objects.all()
    serializer_class = UserPostSerializer

class PurchaseList(generics.RetrieveAPIView):
    queryset = VkToken.objects.all()
    serializer_class = TokenSerializer
    lookup_field = 'user_id'

    def get_object(self):
        queryset = self.get_queryset()
        obj = get_object_or_404(queryset, user_id=self.kwargs['user_id'])
        return obj

class PostsList(generics.ListAPIView):
    # queryset = UserPost.objects.filter(date_post__range = (datetime.datetime.now(),datetime.date(2999,1,1)))
    serializer_class = UserPostSerializer
    model = serializer_class.Meta.model
    lookup_field = 'user_id'

    def get_queryset(self):
        user_id = self.kwargs['user_id']
        queryset = self.model.objects.filter(user_id=user_id).filter(date_post__range = (datetime.datetime.now(),datetime.date(2999,1,1))).order_by('date_post')
        return queryset
    